package com.vortex.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.vortex.bean.RegisterVehicleRequest;
import com.vortex.model.TypeVehicleBrand;
import com.vortex.model.TypeVehicleFuel;
import com.vortex.model.TypeVehicleModel;
import com.vortex.model.User;
import com.vortex.model.Vehicle;
import com.vortex.model.VehiclePk;
import com.vortex.repository.TypeVehicleBrandRepository;
import com.vortex.repository.TypeVehicleFuelRepository;
import com.vortex.repository.TypeVehicleModelRepository;
import com.vortex.repository.UserRepository;
import com.vortex.repository.VehicleRepository;

@Service
public class VehicleServices {

	Logger log = LogManager.getLogger(VehicleServices.class);
	
	public VehicleServices(VehicleRepository vehicleRepository, TypeVehicleBrandRepository typeVehicleBrandRepository, TypeVehicleFuelRepository typeVehicleFuelRepository, TypeVehicleModelRepository typeVehicleModelRepository, UserRepository userRepository) {
		this.vehicleRepository = vehicleRepository;
		this.typeVehicleBrandRepository = typeVehicleBrandRepository;
		this.typeVehicleFuelRepository = typeVehicleFuelRepository;
		this.typeVehicleModelRepository = typeVehicleModelRepository;
		this.userRepository = userRepository;
	}
	
	private final VehicleRepository vehicleRepository;
	
	private final TypeVehicleBrandRepository typeVehicleBrandRepository;
	
	private final TypeVehicleFuelRepository typeVehicleFuelRepository;
	
	private final TypeVehicleModelRepository typeVehicleModelRepository;
	
	private final UserRepository userRepository;
	
	
	
	public boolean createVehicle(RegisterVehicleRequest vehicleToRegister, String alias) {
		
		VehiclePk idVehicle = new VehiclePk();
		idVehicle.setVin(vehicleToRegister.getVin());
		idVehicle.setLicensePlate(vehicleToRegister.getLicensePlate());
		
		Vehicle vehicle = new Vehicle();
		vehicle.setId(idVehicle);
		
		TypeVehicleBrand brand = typeVehicleBrandRepository.findById(vehicleToRegister.getIdBrand()).orElseGet(() -> null);
		vehicle.setBrand(brand);
		
		TypeVehicleFuel fuel = typeVehicleFuelRepository.findById(vehicleToRegister.getIdFuel()).orElseGet(() -> null);
		vehicle.setFuel(fuel);
		
		TypeVehicleModel model = typeVehicleModelRepository.findById(vehicleToRegister.getIdModel()).orElseGet(() -> null);
		vehicle.setModel(model);
		
		User user = userRepository.findByAlias(alias);
		vehicle.setUser(user);
		
		vehicle.setCc(vehicleToRegister.getCc());
		vehicle.setRegistrationDate(vehicleToRegister.getRegistrationDate());
		
		
		if(vehicleRepository.existsById(idVehicle)) {
			return false;
		}else {
			vehicleRepository.saveAndFlush(vehicle);
			return true;
		}
	}
	
	public boolean UpdateVehicle(RegisterVehicleRequest vehicleToRegister, String alias) {
		
		VehiclePk idVehicle = new VehiclePk();
		idVehicle.setVin(vehicleToRegister.getVin());
		idVehicle.setLicensePlate(vehicleToRegister.getLicensePlate());
		
		Vehicle vehicle = new Vehicle();
		vehicle.setId(idVehicle);
		
		TypeVehicleBrand brand = typeVehicleBrandRepository.findById(vehicleToRegister.getIdBrand()).orElseGet(() -> null);
		vehicle.setBrand(brand);
		
		TypeVehicleFuel fuel = typeVehicleFuelRepository.findById(vehicleToRegister.getIdFuel()).orElseGet(() -> null);
		vehicle.setFuel(fuel);
		
		TypeVehicleModel model = typeVehicleModelRepository.findById(vehicleToRegister.getIdModel()).orElseGet(() -> null);
		vehicle.setModel(model);
		
		User user = userRepository.findByAlias(alias);
		vehicle.setUser(user);
		
		vehicle.setCc(vehicleToRegister.getCc());
		vehicle.setRegistrationDate(vehicleToRegister.getRegistrationDate());
		
		
		if(vehicleRepository.existsById(idVehicle)) {
			vehicleRepository.saveAndFlush(vehicle);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean deleteVehicle(String vin, String licensePlate) {
		
		VehiclePk idVehicle = new VehiclePk();
		idVehicle.setVin(vin);
		idVehicle.setLicensePlate(licensePlate);
		
		if(vehicleRepository.existsById(idVehicle)) {
			vehicleRepository.deleteById(idVehicle);
			return true;
		}else {
			return false;
		}
	}
	
	public List<Vehicle> readVehicles(String alias) {
		
		User user = userRepository.findByAlias(alias);
		
		if(vehicleRepository.existsByUser(user)) {
			List<Vehicle> vehicles = vehicleRepository.findByUser(user);
			return vehicles;
		}else {
			return null;
		}
	}
	
}
