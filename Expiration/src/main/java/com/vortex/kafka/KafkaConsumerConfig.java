package com.vortex.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.vortex.kafka.model.VehicleMSG;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	 private static final String KAFKA_PORT = "9092";
	 
	 @Bean
	 ConsumerFactory<String, VehicleMSG> consumerFactory() {
		 Map<String, Object> props = new HashMap<>();
	     props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:" + KAFKA_PORT);
	     props.put(ConsumerConfig.GROUP_ID_CONFIG, "reminder-group");
	     props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	     props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

	     JsonDeserializer<VehicleMSG> deserializer = new JsonDeserializer<>(VehicleMSG.class);
	     deserializer.addTrustedPackages("*");

	     return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), deserializer);
	 }

	 @Bean
	 ConcurrentKafkaListenerContainerFactory<String, VehicleMSG> kafkaListenerContainerFactory() {
	 	ConcurrentKafkaListenerContainerFactory<String, VehicleMSG> factory = new ConcurrentKafkaListenerContainerFactory<>();
	    factory.setConsumerFactory(consumerFactory());
	    return factory;
	 }
	
}
