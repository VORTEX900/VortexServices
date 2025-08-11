package com.vortex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vortex.model.Vehicle;
import com.vortex.model.VehiclePk;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, VehiclePk> {

}
