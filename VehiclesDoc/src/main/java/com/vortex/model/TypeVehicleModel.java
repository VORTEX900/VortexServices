package com.vortex.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "type_vehicle_model")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TypeVehicleModel {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 45, nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "brand", referencedColumnName = "id")
    private TypeVehicleBrand brand;
}