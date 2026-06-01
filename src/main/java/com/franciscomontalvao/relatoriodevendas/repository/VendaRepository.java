package com.franciscomontalvao.relatoriodevendas.repository;

import com.franciscomontalvao.relatoriodevendas.projection.RelatorioTotalProjection;
import com.franciscomontalvao.relatoriodevendas.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {


}