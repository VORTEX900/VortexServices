package com.vortex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.vortex.bean.DeleteVehicleRequest;
import com.vortex.bean.ReadVehicleRequest;
import com.vortex.bean.RegisterVehicleRequest;
import com.vortex.model.Vehicle;
import com.vortex.services.VehicleServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
	
	Logger log = LogManager.getLogger(VehicleController.class);
	
	private final VehicleServices vehicleServices;
	
	public VehicleController(VehicleServices vehicleServices) {
		this.vehicleServices = vehicleServices;
	}

	@GetMapping("/serverCheck")
    public ResponseEntity<?> serverCheck() {

        Map<String, Object> response = new HashMap<>();
        response.put("Response", ">>>SUCCESS<<<");

        return ResponseEntity.ok(response);
    }
	
	@PostMapping("/registerVehicle")
	public ResponseEntity<?> registerVehicle (@Valid @RequestBody RegisterVehicleRequest req) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();

		vehicleServices.createVehicle(req, principal.toString());
		
        return ResponseEntity.ok(Map.of("message", "Veicolo registrato con successo!"));
    }
	
	@PostMapping("/updateVehicle")
	public ResponseEntity<?> updateVehicle (@Valid @RequestBody RegisterVehicleRequest req) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();

		vehicleServices.UpdateVehicle(req, principal.toString());
		
        return ResponseEntity.ok(Map.of("message", "Veicolo registrato con successo!"));
    }
	
	@PostMapping("/deleteVehicle")
	public ResponseEntity<?> deleteVehicle (@Valid @RequestBody DeleteVehicleRequest req) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();

		vehicleServices.deleteVehicle(req.getVin(), req.getLicensePlate());
		
        return ResponseEntity.ok(Map.of("message", "Veicolo registrato con successo!"));
    }
	
	@GetMapping("/readVehicle")
	public List<Vehicle> readVehicle (@Valid @RequestBody ReadVehicleRequest req) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();

		List<Vehicle> vehicles = vehicleServices.readVehicles(req.getAlias());
		
        return vehicles;
    }

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
