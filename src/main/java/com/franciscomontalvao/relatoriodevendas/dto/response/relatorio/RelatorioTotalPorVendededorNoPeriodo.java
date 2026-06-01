package com.franciscomontalvao.relatoriodevendas.dto.response.relatorio;

import com.franciscomontalvao.relatoriodevendas.dto.response.VendedorSimplificadoDTO;

import java.util.List;

public record RelatorioTotalPorVendededorNoPeriodo(
        PeriodoDTO periodo,
        List<VendedorSimplificadoDTO> vendedores

) {
}
