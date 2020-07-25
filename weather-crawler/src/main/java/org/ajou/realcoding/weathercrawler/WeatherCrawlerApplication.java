package org.ajou.realcoding.weathercrawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherCrawlerApplication {

	private final String MY_CONSTATNT = "constatnt";

	public static void main(String[] args) {
		SpringApplication.run(WeatherCrawlerApplication.class, args);
	}

}