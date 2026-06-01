package com.franciscomontalvao.relatoriodevendas.controller;


import com.franciscomontalvao.relatoriodevendas.service.RelatorioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/relatorios/vendas")
public class RelatorioController {

    private final RelatorioService service;

    public RelatorioController(RelatorioService service){
        this.service = service;
    }

    @GetMapping("/total")
    public ResponseEntity<?> totalPorPeriodo(@RequestParam("data_inicio") LocalDate dataInicio, @RequestParam("data_fim") LocalDate dataFim) {
        return ResponseEntity.ok(service.filtrarPorPeriodo(dataInicio, dataFim));
    }


    @GetMapping("/por-vendedor")
    public ResponseEntity<?> listaTotalPorVendedores (@RequestParam("data_inicio") LocalDate dataInicio, @RequestParam("data_fim") LocalDate dataFim ){
        return ResponseEntity.ok(service.vendasPorVendedor(dataInicio, dataFim));
    }

    @GetMapping("/diario")
    public ResponseEntity<?> listaDeVendasPorDia (@RequestParam("data_inicio") LocalDate dataInicio, @RequestParam("data_fim") LocalDate dataFim ){
        return ResponseEntity.ok(service.vendasPorDia(dataInicio, dataFim));
    }


}
