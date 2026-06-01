package com.franciscomontalvao.relatoriodevendas.dto.response;

import java.math.BigDecimal;

public record VendedorSimplificadoDTO(
        Integer posicao,
        Long id,
        String nome,
        BigDecimal totalVendido,
        Integer quantidadeVendas
) {
}
