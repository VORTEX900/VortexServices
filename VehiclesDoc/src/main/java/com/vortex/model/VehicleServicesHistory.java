package com.vortex.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "`vehicle_services_history`")
public class VehicleServicesHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "vin", length = 20, nullable = false)
	private String vin;

	@Column(name = "license_plate", length = 10, nullable = false)
	private String licensePlate;

	@Column(name = "km", nullable = false)
	private Integer km;

	@Column(name = "service_date", nullable = false)
	private Date serviceDate;

	@Column(name = "description", length = 100)
	private String description;

	@Column(name = "note", length = 250)
	private String note;

	@Column(name = "expense", precision = 10, scale = 0)
	private BigDecimal expense;


}
