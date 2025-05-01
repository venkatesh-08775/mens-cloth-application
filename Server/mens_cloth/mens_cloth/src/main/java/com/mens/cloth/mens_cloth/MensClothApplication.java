package com.mens.cloth.mens_cloth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MensClothApplication {

	public static void main(String[] args) {
		SpringApplication.run(MensClothApplication.class, args);
	}

}
