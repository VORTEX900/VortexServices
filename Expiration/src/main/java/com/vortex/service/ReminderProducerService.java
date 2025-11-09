package com.vortex.service;



import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.vortex.kafka.model.VehicleMSG;
import com.vortex.scheduler.ReminderProducer;

@Service
public class ReminderProducerService {

	private final ReminderProducer reminderProducer;
	
	Logger log = LogManager.getLogger(ReminderProducerService.class);
	
	@Autowired
	public ReminderProducerService(ReminderProducer reminderProducer) {
		this.reminderProducer = reminderProducer;
	}
	
	@Scheduled(cron = "0 * * * * *")
	public void sendDailyReminders() {
		
		//TODO PARTE MOCKATA PER ORA
		List<VehicleMSG> vehicleMSGs = List.of(
				new VehicleMSG("1A2B3C","FM269SY"),
				new VehicleMSG("5D6E7F","GN953PE")
				);
		
		vehicleMSGs.forEach(reminderProducer::sendReminder);
		
	}
	
	public void sendReminders() {
		
		//TODO PARTE MOCKATA PER ORA
		List<VehicleMSG> vehicleMSGs = List.of(
				new VehicleMSG("1A2B3C","FM269SY"),
				new VehicleMSG("5D6E7F","GN953PE")
				);
		
		vehicleMSGs.forEach(reminderProducer::sendReminder);
		
	}
	
}
