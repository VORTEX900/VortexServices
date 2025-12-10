package com.vortex.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vortex.bean.DeleteVehicleRequest;
import com.vortex.bean.ReadVehicleRequest;
import com.vortex.bean.RegisterVehicleRequest;
import com.vortex.feign.client.FeignCheckClient;
import com.vortex.model.Vehicle;
import com.vortex.services.VehicleServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
	
	Logger log = LogManager.getLogger(VehicleController.class);
	
	private final VehicleServices vehicleServices;
	
	private final FeignCheckClient feignCheckClient;
	
	public VehicleController(VehicleServices vehicleServices, FeignCheckClient feignCheckClient) {
		this.vehicleServices = vehicleServices;
		this.feignCheckClient = feignCheckClient;
	}
	
	@GetMapping("/serverCheck")
    public ResponseEntity<Map<String, Object>> serverCheck() {

        Map<String, Object> response = new HashMap<>();
        response.put("Response", ">>>SUCCESS<<<");

        return ResponseEntity.ok(response);
    }
	
	@PostMapping("/registerVehicle")
	public ResponseEntity<Map<String, String>> registerVehicle (@Valid @RequestBody RegisterVehicleRequest req,
			@RequestHeader("X-User-Id") String userId
		    ) {
		String principal = getPrincipal(userId);

		vehicleServices.createVehicle(req, principal);
		
        return ResponseEntity.ok(Map.of("message", "Veicolo registrato con successo!"));
    }
	
	@PostMapping("/updateVehicle")
	public ResponseEntity<Map<String, String>> updateVehicle (@Valid @RequestBody RegisterVehicleRequest req,
			@RequestHeader("X-User-Id") String userId
		    ) {
		String principal = getPrincipal(userId);

		vehicleServices.updateVehicle(req, principal);
		
        return ResponseEntity.ok(Map.of("message", "Veicolo registrato con successo!"));
    }
	
	@PostMapping("/deleteVehicle")
	public ResponseEntity<Map<String, String>> deleteVehicle (@Valid @RequestBody DeleteVehicleRequest req,
			@RequestHeader("X-User-Id") String userId
		    ) {

		vehicleServices.deleteVehicle(req.getVin(), req.getLicensePlate());
		
        return ResponseEntity.ok(Map.of("message", "Veicolo registrato con successo!"));
    }
	
	@PostMapping("/readVehicle")
	public List<Map<String, Object>> readVehicle (@Valid @RequestBody ReadVehicleRequest req,
			@RequestHeader("X-User-Id") String userId
		    ) {

		List<Vehicle> vehicles = vehicleServices.readVehicles(req.getAlias());
		
		return vehicles.stream()
		        .map(v -> {
		            Map<String, Object> m = new HashMap<>();
		            m.put("vin", v.getId().getVin());
		            m.put("licensePlate", v.getId().getLicensePlate());
		            m.put("brandName", v.getBrand().getName());
		            m.put("modelName", v.getModel().getName());
		            return m;
		        })
		        .toList();
    }

	@GetMapping("/whoami")
	public String whoAmI(@RequestHeader("X-User-Id") String userId) {
		
	    if (isAuthenticated(userId)) {
	    	return "Utente non autenticato";
	    }

	    Object principal = getPrincipal(userId);

	    return "Utente autenticato: " + principal.toString();
	  }
	
    @GetMapping("/call-server-check")
    public ResponseEntity<Map<String, Object>> callServerCheck() {
        Map<String, Object> responseFromY = feignCheckClient.serverCheck();
        return ResponseEntity.ok(responseFromY);
    }
	
	public boolean isAuthenticated(String userId) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			return !(authentication == null || !authentication.isAuthenticated());
		}catch(Exception e) {
			log.error(e.getMessage(), e);
		}
		return !(userId == null || userId.isEmpty());
	}
	
	public String getPrincipal(String userId) {
		
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication == null || !authentication.isAuthenticated()) {
				log.error(">>> authentication is NULL...");
			}else { 
				return authentication.getPrincipal()==null?null:authentication.getPrincipal().toString();
			}
		}catch(Exception e) {
			log.error(e.getMessage(), e);
		}
		if (userId == null || userId.isEmpty()) {
	        return null;
	    }else {
	    	return userId;
	    }
		
	}
	
}
