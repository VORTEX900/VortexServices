package com.vortex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vortex.bean.ReadVehicleRequest;
import com.vortex.feign.client.VehicleFeignClient;
import com.vortex.service.ReminderProducerService;

@RestController
@RequestMapping("/expiration")
public class ExpirationController {
	
	   private final ReminderProducerService reminderProducerService;
	   
	   private final VehicleFeignClient vehicleFeignClient;

	    public ExpirationController(ReminderProducerService reminderProducerService, VehicleFeignClient vehicleFeignClient) {
	        this.reminderProducerService = reminderProducerService;
	        this.vehicleFeignClient = vehicleFeignClient;
	    }

	    @PostMapping("/trigger")
	    public String triggerExpirationJob(@RequestBody ReadVehicleRequest req,
	            @RequestHeader("X-User-Id") String userId) {
	    	List<Map<String, Object>> responseFromY = vehicleFeignClient.readVehicle(req, userId);
	    	
	    	 if (!responseFromY.isEmpty()) {
	    	        Map<String, Object> firstVehicle = responseFromY.get(0);
	    	        String vin = (String) firstVehicle.get("vin");
	    	        String plate = (String) firstVehicle.get("licensePlate");
	    	
	    	        reminderProducerService.sendReminders(vin, plate);
	    	 }
	        return "reminderProducerService job triggered!";
	    }
	    
	    @GetMapping("/serverCheck")
	    public ResponseEntity<Map<String, Object>> serverCheck() {

	        Map<String, Object> response = new HashMap<>();
	        response.put("Response", ">>>SUCCESS<<<");

	        return ResponseEntity.ok(response);
	    }
}
