package com.vortex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vortex.model.TypeVehicleBrand;

@Repository
public interface TypeVehicleBrandRepository extends JpaRepository<TypeVehicleBrand, Integer> {

}
