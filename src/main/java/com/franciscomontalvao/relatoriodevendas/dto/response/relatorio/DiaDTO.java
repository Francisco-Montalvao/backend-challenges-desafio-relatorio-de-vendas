package com.franciscomontalvao.relatoriodevendas.dto.response.relatorio;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DiaDTO(
        LocalDate data,
        BigDecimal totalVendido,
        Integer quantidadeVenddas
) {
}
