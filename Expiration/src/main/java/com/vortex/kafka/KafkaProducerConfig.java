package com.vortex.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaProducerConfig {

	private static final String KAFKA_PORT = "9092";
	
	@Bean
	ProducerFactory<String, Object> produceFactory(){
		Map<String, Object> configProperties = new HashMap<String, Object>();
		configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:"+KAFKA_PORT);
		configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return new DefaultKafkaProducerFactory<String, Object>(configProperties);
	}
	
	@Bean
	KafkaTemplate<String, Object> kafkaTemplate(){
		return new KafkaTemplate<>(produceFactory());
	}
	
}
