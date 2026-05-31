package com.franciscomontalvao.relatoriodevendas.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record VendaRequestDTO(

		@NotNull(message = "nao pode ser nulo")
		@Positive
		Long vendedorId,


		@Positive(message = "valor total nao pode ser negativo")
		@NotNull(message = "nao pode ser nulo")
		BigDecimal valorTotal,

		@NotNull(message = "data e obrigatoria")
		@FutureOrPresent
		LocalDate data
) {
}
