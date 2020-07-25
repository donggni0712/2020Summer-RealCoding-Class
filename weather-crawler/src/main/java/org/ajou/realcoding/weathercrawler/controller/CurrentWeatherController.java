package org.ajou.realcoding.weathercrawler.controller;

import org.ajou.realcoding.weathercrawler.Service.CurrentWeatherService;
import org.ajou.realcoding.weathercrawler.domain.CurrentWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController //스프링아 이거를 객체화해서 갖고 있어줘
public class CurrentWeatherController {

    @Autowired //스프링아 이 객체를 넣어줘
    private CurrentWeatherService currentWeatherService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/weather-service/weathers")
    public CurrentWeather getCurrentWeather(@RequestParam String cityName){
        return currentWeatherService.getCurrentWeather(cityName);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/weather-service/available-cities")
    public List<String> getAvailableCities() throws IOException {
        return currentWeatherService.getAvailableCities();
    }
}
