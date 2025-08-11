package com.vortex.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private VehiclePk id;
	
	@Column(name = "cc", nullable = false)
    private int cc;

	@Column(name = "registration_date")
    private Date registrationDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "brand", referencedColumnName = "id")
    private TypeVehicleBrand brand;

    @ManyToOne(optional = false)
    @JoinColumn(name = "model", referencedColumnName = "id")
    private TypeVehicleModel model;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fuel", referencedColumnName = "id")
    private TypeVehicleFuel fuel;

    @ManyToOne
    @JoinColumn(name = "emission", referencedColumnName = "id")
    private TypeVehicleEmission emission;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
	
}
