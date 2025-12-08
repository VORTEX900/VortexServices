package com.vortex.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.vortex.kafka.model.VehicleMSG;

@Component
public class ReminderProducer {
	
	private final KafkaTemplate<String, Object> kafkaTemplate;
	
	Logger log = LogManager.getLogger(ReminderProducer.class);
	
	public ReminderProducer(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendReminder(VehicleMSG vehicleMSG) {
		kafkaTemplate.send("reminder", vehicleMSG.getVin()+"/"+vehicleMSG.getLicensePlate(), vehicleMSG);
		log.info(">>>Reminder Inviato<<<");
	}
	

}
