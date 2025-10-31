package com.vortex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vortex.model.TypeVehicleFuel;

@Repository
public interface TypeVehicleFuelRepository extends JpaRepository<TypeVehicleFuel, Integer> {

}
