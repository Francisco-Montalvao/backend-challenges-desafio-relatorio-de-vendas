package com.franciscomontalvao.relatoriodevendas.dto.response.relatorio;

import java.math.BigDecimal;

public record RelatorioTotalPorPeriodoResponseDTO(
        BigDecimal totalVendido,
        Integer quantidadeDeVendas,
        PeriodoDTO periodo
){
}
