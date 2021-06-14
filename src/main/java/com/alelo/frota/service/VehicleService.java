package com.alelo.frota.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alelo.frota.exception.DuplicatedPlateException;
import com.alelo.frota.exception.VehicleNotFoundException;
import com.alelo.frota.model.Vehicle;
import com.alelo.frota.repository.VehicleRepository;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository repository;

	public void deleteEntity(Long id) throws VehicleNotFoundException {
		Vehicle entity = this.getVehicleById(id);

		this.repository.delete(entity);
	}

	public Vehicle getEntity(Vehicle entity, Vehicle vehicleToSave) {
		Vehicle newEntity = new Vehicle();

		newEntity.setId(entity.getId());
		newEntity.setColor(vehicleToSave.getColor());
		newEntity.setManufacturer(vehicleToSave.getManufacturer());
		newEntity.setModel(vehicleToSave.getModel());
		newEntity.setPlate(entity.getPlate());
		newEntity.setStatus(vehicleToSave.getStatus());

		return newEntity;
	}

	public Vehicle getVehicleById(Long id) throws VehicleNotFoundException {
		Optional<Vehicle> optVehicle = repository.findById(id);

		if (optVehicle.isEmpty()) {
			throw new VehicleNotFoundException("Vehicle with id " + id + " not found");
		}

		return optVehicle.get();
	}

	public Page<Vehicle> getVehicles(String plate, Boolean status, Pageable pageable) {

		if ((plate != null && !plate.isEmpty()) && status != null) {
			return repository.findByPlateContainsIgnoreCaseAndStatus(plate, status, pageable);
		} else if (plate != null && !plate.isEmpty()) {
			return repository.findByPlateContainsIgnoreCase(plate, pageable);
		} else if (status != null) {
			return repository.findByStatus(status, pageable);
		}

		return repository.findAll(pageable);
	}

	public Vehicle saveVehicle(Vehicle vehicleToSave) throws DuplicatedPlateException {
		vehicleToSave.setId(null);
		
		Vehicle plate = this.repository.findByPlateIgnoreCase(vehicleToSave.getPlate());
		
		if(plate != null) {
			throw new DuplicatedPlateException("Already exists vehicle with plate " + vehicleToSave.getPlate() + ".");
		}
		
		return this.repository.save(vehicleToSave);
	}

	public Vehicle updateVehicle(Long id, Vehicle vehicleToSave) throws DuplicatedPlateException, VehicleNotFoundException {

		Vehicle entity = this.getVehicleById(id);

		Vehicle entityToSave = this.getEntity(entity, vehicleToSave);

		return this.repository.save(entityToSave);
	}

}
