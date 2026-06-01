package com.franciscomontalvao.relatoriodevendas.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface RelatorioVendasPorDiaProjection {
    LocalDate getData();
    BigDecimal getTotalVendido();
    Integer getQuantidadeVendas();
}
