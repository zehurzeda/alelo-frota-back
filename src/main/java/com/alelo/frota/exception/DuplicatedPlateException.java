package com.alelo.frota.exception;

public class DuplicatedPlateException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DuplicatedPlateException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

	public DuplicatedPlateException(String errorMessage) {
		super(errorMessage);
	}

}
