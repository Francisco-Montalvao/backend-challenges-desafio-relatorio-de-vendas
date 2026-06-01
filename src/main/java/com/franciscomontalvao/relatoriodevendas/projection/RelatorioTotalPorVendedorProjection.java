package com.franciscomontalvao.relatoriodevendas.projection;

import java.math.BigDecimal;

public interface RelatorioTotalPorVendedorProjection {

    Long getId();
    String getNome();
    BigDecimal getTotalVendido();
    Integer getQuantidadeVendas();


}
