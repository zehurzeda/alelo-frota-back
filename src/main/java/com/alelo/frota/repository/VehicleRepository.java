package com.alelo.frota.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.alelo.frota.model.Vehicle;

public interface VehicleRepository extends PagingAndSortingRepository<Vehicle, Long> {

	Page<Vehicle> findByPlateContainsIgnoreCase(String plate, Pageable pageable);
	
	Page<Vehicle> findByStatus(Boolean status, Pageable pageable);
	
	Vehicle findByPlateIgnoreCase(String plate);
	
	Page<Vehicle> findByPlateContainsIgnoreCaseAndStatus(String plate, Boolean status, Pageable pageable);

}
