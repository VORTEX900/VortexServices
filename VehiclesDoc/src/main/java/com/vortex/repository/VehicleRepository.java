package com.vortex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vortex.model.User;
import com.vortex.model.Vehicle;
import com.vortex.model.VehiclePk;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, VehiclePk> {

	List<Vehicle> findByUser(User user);
	
	boolean existsByUser(User user);
	
}
