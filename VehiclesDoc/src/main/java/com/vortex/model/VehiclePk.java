package com.vortex.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
@Embeddable
public class VehiclePk implements Serializable {
   
	private static final long serialVersionUID = 1L;

	@Column(name = "vin")
	private String vin;
	
	@Column(name = "licensePlate")
    private String licensePlate;

}
