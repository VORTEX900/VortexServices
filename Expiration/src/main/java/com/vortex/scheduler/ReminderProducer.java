package com.vortex.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vortex.kafka.model.VehicleMSG;
import com.vortex.service.ReminderProducerService;

@Component
public class ReminderProducer {
	
	private final ReminderProducerService reminderProducerService;
	
	public ReminderProducer(ReminderProducerService reminderProducerService) {
		this.reminderProducerService = reminderProducerService;
	}
	
	@Scheduled(cron = "0 * * * * *")
	public void sendDailyReminders() {
		
		//TODO PARTE MOCKATA PER ORA
		List<VehicleMSG> vehicleMSGs = List.of(
				new VehicleMSG("1A2B3C","FM269SY"),
				new VehicleMSG("5D6E7F","GN953PE")
				);
		
		vehicleMSGs.forEach(reminderProducerService::sendReminder);
		
	}

}
