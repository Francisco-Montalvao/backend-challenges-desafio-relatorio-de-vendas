package com.franciscomontalvao.relatoriodevendas.dto.response.relatorio;

import java.util.List;

public record RelatorioVendasPorDia(
        PeriodoDTO periodo,
        List<DiaDTO> dias
) {
}
