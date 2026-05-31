package com.franciscomontalvao.relatoriodevendas.dto.erro;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.List;

public record ErroPadrao (
		LocalDate timestamp,
		Integer status,
		String mensagem,
		@JsonInclude(JsonInclude.Include.NON_NULL)
		String path,
		@JsonInclude(JsonInclude.Include.NON_NULL)
		List<Erros> erros
){
}
