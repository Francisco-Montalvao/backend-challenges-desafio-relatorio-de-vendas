package com.franciscomontalvao.relatoriodevendas.exception;


import com.franciscomontalvao.relatoriodevendas.dto.erro.ErroPadrao;
import com.franciscomontalvao.relatoriodevendas.dto.erro.Erros;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErroPadrao> handleValidacao(MethodArgumentNotValidException ex, HttpServletRequest http){

		ErroPadrao erro = new ErroPadrao(
				LocalDate.now(),
				HttpStatus.BAD_REQUEST.value(),
				"Erro validacao",
				http.getServletPath(),
				ex.getFieldErrors()
						.stream()
						.map( error ->
								new Erros(
								error.getField(),
								error.getDefaultMessage()))
						.toList()
		);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErroPadrao> jaExistente(DataIntegrityViolationException ex, HttpServletRequest http){
		ErroPadrao erro = new ErroPadrao(
				LocalDate.now(),
				HttpStatus.CONFLICT.value(),
				"Já existe um registro com esses dados",
				http.getServletPath(),
				null
		);

		return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
	}


	@ExceptionHandler(VendedorException.class)
	public ResponseEntity<ErroPadrao> vendedorNaoEcontrado (VendedorException ex, HttpServletRequest http){
		ErroPadrao erro = new ErroPadrao(
				LocalDate.now(),
				ex.getStatus().value(),
				ex.getMessage(),
				http.getServletPath(),
				null
		);
		return ResponseEntity.status(ex.getStatus()).body(erro);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErroPadrao> rotaComMetodoInexistnte(HttpRequestMethodNotSupportedException ex){
		ErroPadrao erro = new ErroPadrao(
				LocalDate.now(),
				HttpStatus.METHOD_NOT_ALLOWED.value(),
				ex.getMessage(),
				null,
				null
		);
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED.value()).body(erro);
	}
}
