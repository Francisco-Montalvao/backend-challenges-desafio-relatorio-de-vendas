package com.franciscomontalvao.relatoriodevendas.exception;

import org.springframework.http.HttpStatus;

public class VendedorException extends RuntimeException {
	private final HttpStatus status;

	public VendedorException(String message, HttpStatus httpStatus) {
		super(message);

		this.status = httpStatus;
	}

	public HttpStatus getStatus() {
		return status;
	}
}
