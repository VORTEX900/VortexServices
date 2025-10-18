package com.vortex.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.vortex.kafka.model.VehicleMSG;

@Component
public class ReminderConsumer {
	
	Logger log = LogManager.getLogger(ReminderConsumer.class);
	
	@KafkaListener(topics = "reminder", groupId = "reminder-group")
	public void listen(VehicleMSG message) {
		log.info("Ricevuto messaggio: " + message.getVin() + ", targa: " + message.getLicensePlate());
		
	}
	
}
