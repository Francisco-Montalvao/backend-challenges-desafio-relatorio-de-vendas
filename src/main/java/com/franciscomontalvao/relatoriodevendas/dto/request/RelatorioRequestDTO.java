package com.franciscomontalvao.relatoriodevendas.dto.request;

import java.time.LocalDate;

public record RelatorioRequestDTO(
        LocalDate dataInicio,
        LocalDate dataFim
) {
}
