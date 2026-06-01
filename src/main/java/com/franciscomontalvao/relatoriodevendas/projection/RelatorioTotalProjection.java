package com.franciscomontalvao.relatoriodevendas.projection;

import java.math.BigDecimal;

public interface RelatorioTotalProjection {
    BigDecimal getTotal();
    Integer getQuantidade();
}
