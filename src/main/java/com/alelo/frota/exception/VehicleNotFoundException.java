package com.alelo.frota.exception;

public class VehicleNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VehicleNotFoundException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

	public VehicleNotFoundException(String errorMessage) {
		super(errorMessage);
	}

}
