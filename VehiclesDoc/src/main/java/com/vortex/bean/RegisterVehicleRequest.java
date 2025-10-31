package com.vortex.bean;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterVehicleRequest {
	
	@Size(min = 17, max = 17, message = "Vin length is 17")
	@NotBlank(message = "Vin is mandatory")
	private String vin;
    
	@NotBlank(message = "LicensePlate is mandatory")
	private String licensePlate;

	@Positive(message = "The cc must be positive")
    private int cc;

	@Past(message = "The registration date must be in the past")
    private Date registrationDate;
    
    @NotNull(message = "Brand is mandatory")
    private Integer idBrand;
    
    private Integer idModel;
    
    private Integer IdFuel;
    
    private Integer idEmission;

   

    
}
