package com.franciscomontalvao.relatoriodevendas.repository;


import com.franciscomontalvao.relatoriodevendas.model.Venda;
import com.franciscomontalvao.relatoriodevendas.projection.RelatorioTotalPorVendedorProjection;
import com.franciscomontalvao.relatoriodevendas.projection.RelatorioTotalProjection;
import com.franciscomontalvao.relatoriodevendas.projection.RelatorioVendasPorDiaProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RelatorioRepository extends Repository<Venda, Long> {

    @Query(value =
            "SELECT SUM(valor_total) as total, COUNT(*) as quantidade " +
                    "FROM vendas " +
                    "WHERE data >= :dataInicio AND data <= :dataFim",
            nativeQuery = true)
    public RelatorioTotalProjection findByDataInicioAndDataFim(
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim
    );


    @Query(
            value = """
                    SELECT
                            v.id,
                            v.nome,
                            SUM(ve.valor_total) AS totalVendido,
                            COUNT(*)            AS quantidadeVendas
                        FROM vendas ve
                        JOIN vendedores v ON v.id = ve.vendedor_id
                        WHERE ve.data BETWEEN :dataInicio AND :dataFim
                        GROUP BY v.id, v.nome
                        ORDER BY totalVendido DESC 
                    """, nativeQuery = true)
    public List<RelatorioTotalPorVendedorProjection> porVendedor(
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim
    );

    @Query(
            value = """
                    SELECT
                        data,
                        SUM(valor_total) AS totalVendido,
                        COUNT(*)         AS quantidadeVendas
                    FROM vendas
                    WHERE data BETWEEN :dataInicio AND :dataFim
                    GROUP BY data
                    ORDER BY data ASC
                    """, nativeQuery = true)
    public List<RelatorioVendasPorDiaProjection> vendasPorDia(
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim
    );


}
