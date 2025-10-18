package com.vortex.service;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.vortex.kafka.model.VehicleMSG;

@Service
public class ReminderProducerService {

	private final KafkaTemplate<String, Object> kafkaTemplate;
	Logger log = LogManager.getLogger(ReminderProducerService.class);
	
	@Autowired
	public ReminderProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void sendReminder(VehicleMSG vehicleMSG) {
		kafkaTemplate.send("reminder", vehicleMSG.getVin()+"/"+vehicleMSG.getLicensePlate(), vehicleMSG);
		log.info(">>>Reminder Inviato<<<");
	}
	
}
