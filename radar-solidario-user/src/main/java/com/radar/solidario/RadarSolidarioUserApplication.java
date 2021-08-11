package com.radar.solidario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

//@EnableCaching
@SpringBootApplication
public class RadarSolidarioUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(RadarSolidarioUserApplication.class, args);
	}
}
