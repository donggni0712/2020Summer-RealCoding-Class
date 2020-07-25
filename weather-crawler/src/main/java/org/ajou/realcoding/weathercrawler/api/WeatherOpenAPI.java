package org.ajou.realcoding.weathercrawler.api;

import org.ajou.realcoding.weathercrawler.domain.CurrentWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherOpenAPI {

    @Autowired  //config에서 주입을 해줬기 때문에 autowired가능.
    private RestTemplate restTemplate;//스프링에 resttemplate를 등록
    private static final String WEATEHER_REQUEST_URI = "https://api.openweathermap.org/data/2.5/weather?q={cityName}&appid=4f70e0f162a165504fc04d3024d06cb0";

    public CurrentWeather getCurrentWeather(String cityName) {
        CurrentWeather currentWeather = restTemplate.getForObject(WEATEHER_REQUEST_URI, CurrentWeather.class, cityName); //ctrl + P

        return currentWeather;

    }
}
