package com.vortex.feign.client;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.vortex.bean.ReadVehicleRequest;

@FeignClient(name = "vehicles-service", url = "${feign.vehicles-uri}")
public interface VehicleFeignClient {
	
	 @PostMapping("/vehicle/readVehicle")
	 List<Map<String, Object>> readVehicle(@RequestBody ReadVehicleRequest req,
		        @RequestHeader("X-User-Id") String userId);

}
