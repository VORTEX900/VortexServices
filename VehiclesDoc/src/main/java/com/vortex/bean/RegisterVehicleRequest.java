package com.vortex.bean;

import java.util.Date;

import com.vortex.model.TypeVehicleBrand;
import com.vortex.model.TypeVehicleEmission;
import com.vortex.model.TypeVehicleFuel;
import com.vortex.model.TypeVehicleModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterVehicleRequest {
	
	private String vin;
    private String licensePlate;
    private int cc;
    private Date registrationDate;
    private TypeVehicleBrand brand;
    private TypeVehicleModel model;
    private TypeVehicleFuel fuel;
    private TypeVehicleEmission emission;

   

    
}
