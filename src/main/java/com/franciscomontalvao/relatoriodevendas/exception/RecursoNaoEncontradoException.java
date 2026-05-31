package com.franciscomontalvao.relatoriodevendas.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RecursoNaoEncontradoException extends RuntimeException {
	private final HttpStatus status;

	public RecursoNaoEncontradoException(String message, HttpStatus httpStatus) {
		super(message);

		this.status = httpStatus;
	}

}
