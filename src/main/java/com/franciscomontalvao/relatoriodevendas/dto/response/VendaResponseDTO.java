package com.franciscomontalvao.relatoriodevendas.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record VendaResponseDTO(
		Long id,
		Long vendedorId,
		BigDecimal valorTotal,
		LocalDate data
) {
}
