package org.ajou.realcoding.weathercrawler.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ajou.realcoding.weathercrawler.api.WeatherOpenAPI;
import org.ajou.realcoding.weathercrawler.domain.City;
import org.ajou.realcoding.weathercrawler.domain.CurrentWeather;
import org.ajou.realcoding.weathercrawler.repository.CurrentWeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

@Service
@EnableScheduling
public class CurrentWeatherService {

    @Autowired
    private ObjectMapper objectMapper; //Json을 읽어서 java object로 변환해줄 수 있음.

    @Autowired
    private WeatherOpenAPI weatherOpenAPI;

    @Autowired
    private CurrentWeatherRepository currentWeatherRepository;

    private Queue<String> cityNamesQueue = new LinkedList<>();

    @PostConstruct //spring이 처음 뜨는 단계에서 실행해줌.
    public void setUpCityNames() throws IOException {
        List<String> availableCityNames = this.getAvailableCities();
        cityNamesQueue.addAll(availableCityNames);
    }

    @GetMapping("/weather-service/Available-cities")
    public List<String> getAvailableCities() throws IOException {
    List<City> cities = objectMapper.readValue(new File("city.list.json"), new TypeReference<List<City>>() {
    });

    return cities.stream()
            .filter(city -> city.getCountry().equals("KR"))
            .map(city -> city.getName())
            .collect(Collectors.toList());
    }
    @GetMapping("/weather-service/weathers")
    public CurrentWeather getCurrentWeather(String cityName) {
        //비즈니스 모델 추가 가능(available).
        return weatherOpenAPI.getCurrentWeather(cityName);
    }


    @Scheduled(fixedDelay = 2000L)
    public void getCurrentWeatherEveryTwoSeconds(){
        String targetCityName = cityNamesQueue.poll();
        cityNamesQueue.add(targetCityName);

        CurrentWeather currentWeather = weatherOpenAPI.getCurrentWeather(targetCityName);
        currentWeatherRepository.saveCurrentWeahter(currentWeather);
    }
}
