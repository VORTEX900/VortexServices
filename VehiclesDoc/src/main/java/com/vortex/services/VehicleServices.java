package com.vortex.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vortex.bean.RegisterVehicleRequest;
import com.vortex.model.TypeVehicleBrand;
import com.vortex.model.TypeVehicleFuel;
import com.vortex.model.TypeVehicleModel;
import com.vortex.model.Vehicle;
import com.vortex.model.VehiclePk;
import com.vortex.repository.TypeVehicleBrandRepository;
import com.vortex.repository.TypeVehicleFuelRepository;
import com.vortex.repository.TypeVehicleModelRepository;
import com.vortex.repository.VehicleRepository;

@Service
public class VehicleServices {

	Logger log = LogManager.getLogger(VehicleServices.class);
	
	@Autowired
	public VehicleServices(VehicleRepository vehicleRepository, TypeVehicleBrandRepository typeVehicleBrandRepository, TypeVehicleFuelRepository typeVehicleFuelRepository, TypeVehicleModelRepository typeVehicleModelRepository) {
		this.vehicleRepository = vehicleRepository;
		this.typeVehicleBrandRepository = typeVehicleBrandRepository;
		this.typeVehicleFuelRepository = typeVehicleFuelRepository;
		this.typeVehicleModelRepository = typeVehicleModelRepository;
	}
	
	private final VehicleRepository vehicleRepository;
	
	private final TypeVehicleBrandRepository typeVehicleBrandRepository;
	
	private final TypeVehicleFuelRepository typeVehicleFuelRepository;
	
	private final TypeVehicleModelRepository typeVehicleModelRepository;
	
	
	
	public boolean insertVehicle(RegisterVehicleRequest vehicleToRegister, String user) {
		
		VehiclePk idVehicle = new VehiclePk();
		idVehicle.setVin(vehicleToRegister.getVin());
		idVehicle.setLicensePlate(vehicleToRegister.getLicensePlate());
		
		Vehicle vehicle = new Vehicle();
		vehicle.setId(idVehicle);
		
		log.info("Entro2");
		TypeVehicleBrand brand = typeVehicleBrandRepository.findById(vehicleToRegister.getIdBrand()).orElseGet(() -> null);
		vehicle.setBrand(brand);
		
		TypeVehicleFuel fuel = typeVehicleFuelRepository.findById(vehicleToRegister.getIdFuel()).orElseGet(() -> null);
		vehicle.setFuel(fuel);
		
		TypeVehicleModel model = typeVehicleModelRepository.findById(vehicleToRegister.getIdModel()).orElseGet(() -> null);
		vehicle.setModel(model);
		
		
		vehicle.setCc(vehicleToRegister.getCc());
		vehicle.setRegistrationDate(vehicleToRegister.getRegistrationDate());
		
		
		if(vehicleRepository.existsById(idVehicle)) {
			return false;
		}else {
			vehicleRepository.saveAndFlush(vehicle);
			return true;
		}
	}
	
}
