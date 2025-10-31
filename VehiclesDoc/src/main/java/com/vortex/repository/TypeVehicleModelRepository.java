package com.vortex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vortex.model.TypeVehicleModel;

@Repository
public interface TypeVehicleModelRepository extends JpaRepository<TypeVehicleModel, Integer> {

}
