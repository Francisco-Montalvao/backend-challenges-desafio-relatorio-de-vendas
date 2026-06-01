package com.franciscomontalvao.relatoriodevendas.dto.response.relatorio;

import java.time.LocalDate;

public record PeriodoDTO (
        LocalDate inicio,
        LocalDate fim
){
}
