package com.alelo.frota.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.alelo.frota.exception.DuplicatedPlateException;
import com.alelo.frota.exception.VehicleNotFoundException;
import com.alelo.frota.model.Vehicle;
import com.alelo.frota.service.VehicleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/vehicle")
@Api(value = "Vehicle")
public class VehicleController {

	@Autowired
	private VehicleService service;

	@GetMapping()
	@ApiOperation(value = "Get all vehicles")
	public Page<Vehicle> getAllVehicles(@RequestParam(required = false) String plate,
			@RequestParam(required = false) Boolean status, Pageable pageable) {
		return this.service.getVehicles(plate, status, pageable);
	}

	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Get vehicle by id")
	public Vehicle getVehicleById(@PathVariable final long id) {
		try {			
			return this.service.getVehicleById(id);
		} catch (VehicleNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		} 
	}

	@PostMapping()
	@ApiOperation(value = "Save vehicle")
	public Vehicle saveVehicle(@RequestBody Vehicle vehicleToSave) {
		try {			
			return this.service.saveVehicle(vehicleToSave);
		} catch (DuplicatedPlateException duplicatedPlateExcpt) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, duplicatedPlateExcpt.getMessage());
		}
	}

	@PutMapping(path = "/{id}")
	@ApiOperation(value = "Edit vehicle")
	public Vehicle editVehicle(@PathVariable final long id, @RequestBody Vehicle vehicleToSave) {
		try {
			return this.service.updateVehicle(id, vehicleToSave);
		} catch (VehicleNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		} catch (DuplicatedPlateException duplicatedPlateExcpt) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, duplicatedPlateExcpt.getMessage());
		}
	}

	@DeleteMapping(path = "/{id}")
	@ApiOperation(value = "delete vehicle")
	public void deleteVehicle(@PathVariable final long id) {
		try {
			this.service.deleteEntity(id);
		} catch (VehicleNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}
	}

}
