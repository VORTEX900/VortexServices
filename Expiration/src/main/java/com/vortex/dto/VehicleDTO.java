package com.vortex.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class VehicleDTO {
	
	private String vin;
    private String licensePlate;
    private String brandName;
    private String modelName;

}
