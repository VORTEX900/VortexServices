package com.vortex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {KafkaAutoConfiguration.class})
@EnableScheduling
@EnableFeignClients
public class ExpirationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpirationApplication.class, args);
		
	}

}
