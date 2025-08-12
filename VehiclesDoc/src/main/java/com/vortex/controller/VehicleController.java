package com.vortex.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vortex.bean.RegisterVehicleRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

	@GetMapping("/serverCheck")
    public ResponseEntity<?> serverCheck() {

        Map<String, Object> response = new HashMap<>();
        response.put("Response", ">>>SUCCESS<<<");

        return ResponseEntity.ok(response);
    }
	
//	@PostMapping("/registerVehicle")
//	public ResponseEntity<?> registerVehicle (@Valid @RequestBody RegisterVehicleRequest vehicle) {
//       
//
//        return ResponseEntity.ok(Map.of("message", "Veicolo registrato con successo!"));
//    }
//	
	 @GetMapping("/whoami")
	 public String whoAmI() {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        
	        if (authentication == null || !authentication.isAuthenticated()) {
	            return "Utente non autenticato";
	        }

	        // Qui assumiamo che il principal sia lo userId (string o Long)
	        Object principal = authentication.getPrincipal();

	        return "Utente autenticato: " + principal.toString();
	    }
	
}
