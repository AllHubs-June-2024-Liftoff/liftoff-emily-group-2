package com.notsauce.parkd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.notsauce.parkd")
public class ParkdApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkdApplication.class, args);
	}

}
