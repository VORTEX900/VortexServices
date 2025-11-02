package com.vortex.bean;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeleteVehicleRequest {
	
	@Size(min = 17, max = 17, message = "Vin length is 17")
	@NotBlank(message = "Vin is mandatory")
	private String vin;
    
	@NotBlank(message = "LicensePlate is mandatory")
	private String licensePlate;

}
