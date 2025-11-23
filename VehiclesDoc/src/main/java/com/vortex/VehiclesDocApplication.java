package com.vortex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class VehiclesDocApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehiclesDocApplication.class, args);
	}

}
