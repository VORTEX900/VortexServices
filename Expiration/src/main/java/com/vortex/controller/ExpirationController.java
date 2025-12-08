package com.vortex.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vortex.service.ReminderProducerService;

@RestController
@RequestMapping("/expiration")
public class ExpirationController {
	
	   private final ReminderProducerService reminderProducerService;

	    public ExpirationController(ReminderProducerService reminderProducerService) {
	        this.reminderProducerService = reminderProducerService;
	    }

	    @PostMapping("/trigger")
	    public String triggerExpirationJob() {
	    	reminderProducerService.sendReminders();
	        return "reminderProducerService job triggered!";
	    }
	    
	    @GetMapping("/serverCheck")
	    public ResponseEntity<Map<String, Object>> serverCheck() {

	        Map<String, Object> response = new HashMap<>();
	        response.put("Response", ">>>SUCCESS<<<");

	        return ResponseEntity.ok(response);
	    }
}
