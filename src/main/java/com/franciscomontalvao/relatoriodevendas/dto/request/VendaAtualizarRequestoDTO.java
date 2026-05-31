package com.franciscomontalvao.relatoriodevendas.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record VendaAtualizarRequestoDTO(
		@JsonAlias("valor_total")
		@NotNull(message = "nao pode ser nulo")
		@Positive(message = "nao pode ser negativo")
		BigDecimal valorTotal
) {
}
