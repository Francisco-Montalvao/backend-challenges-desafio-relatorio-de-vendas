package com.franciscomontalvao.relatoriodevendas.service;


import com.franciscomontalvao.relatoriodevendas.dto.response.relatorio.*;
import com.franciscomontalvao.relatoriodevendas.dto.response.VendedorSimplificadoDTO;
import com.franciscomontalvao.relatoriodevendas.repository.RelatorioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.stream.IntStream;

@Transactional(readOnly = true)
@Service
public class RelatorioService {
    private final RelatorioRepository repository;

    public RelatorioService (RelatorioRepository repository){
        this.repository = repository;
    }


    public RelatorioTotalPorPeriodoResponseDTO filtrarPorPeriodo (LocalDate dataInicio, LocalDate dataFim){
        var response  = repository.findByDataInicioAndDataFim(dataInicio, dataFim);

        return new RelatorioTotalPorPeriodoResponseDTO(
                response.getTotal(),
                response.getQuantidade(),
                new PeriodoDTO(
                        dataInicio,
                        dataFim
                )
        );

    }

    public RelatorioTotalPorVendededorNoPeriodo vendasPorVendedor (LocalDate dataInicio, LocalDate dataFim){
        var response = repository.porVendedor(dataInicio, dataFim);

        return new RelatorioTotalPorVendededorNoPeriodo(
                new PeriodoDTO(
                        dataInicio,
                        dataFim
                ),
                IntStream.range(0, response.size())
                        .mapToObj(i -> new VendedorSimplificadoDTO(
                                i+1,
                                response.get(i).getId(),
                                response.get(i).getNome(),
                                response.get(i).getTotalVendido(),
                                response.get(i).getQuantidadeVendas()
                        )).toList()


        );
    }

    public RelatorioVendasPorDia vendasPorDia (LocalDate dataInicio, LocalDate dataFim){
        var response = repository.vendasPorDia(dataInicio, dataFim);

        return new RelatorioVendasPorDia(
                new PeriodoDTO(
                        dataInicio,
                        dataFim
                ),
                response
                        .stream()
                        .map( x -> new DiaDTO(
                                x.getData(),
                                x.getTotalVendido(),
                                x.getQuantidadeVendas()
                        )).toList()
        );
    }

}































