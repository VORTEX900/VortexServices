package com.vortex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication
@SpringBootApplication(exclude = {KafkaAutoConfiguration.class})
@EnableScheduling
public class ExpirationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpirationApplication.class, args);
		
	}

}
