package com.alelo.frota.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alelo.frota.model.Vehicle;
import com.alelo.frota.repository.VehicleRepository;

@SpringBootTest
class VehicleServiceTest {
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private VehicleRepository repository;
	
	@Test
	public void insertVehicle() {
		Vehicle vehicle = new Vehicle();
		vehicle.setColor("black");
		vehicle.setManufacturer("Mercedes-Benz");
		vehicle.setModel("Class C 1.1 Avantgarde Turbo Flex");
		vehicle.setPlate("BBC-1237");
		vehicle.setStatus(true);
		
		Vehicle savedVehicle = this.vehicleService.saveVehicle(vehicle);
		
		Vehicle findedVehicle = this.repository.findByPlate("BBC-1237");
		
		assertNotNull(findedVehicle);
		
		assertEquals(savedVehicle.getManufacturer(), vehicle.getManufacturer());
		assertEquals(savedVehicle.getModel(), vehicle.getModel());
		assertEquals(savedVehicle.getPlate(), vehicle.getPlate());
		assertEquals(savedVehicle.getStatus(), vehicle.getStatus());
		
		assertEquals(savedVehicle, findedVehicle);
		
	}

}
