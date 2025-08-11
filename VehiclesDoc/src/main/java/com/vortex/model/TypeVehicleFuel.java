package com.vortex.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "type_vehicle_fuel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TypeVehicleFuel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 45, nullable = false, unique = true)
    private String name;
}