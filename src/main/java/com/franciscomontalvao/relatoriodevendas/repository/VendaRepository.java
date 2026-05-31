package com.franciscomontalvao.relatoriodevendas.repository;


import com.franciscomontalvao.relatoriodevendas.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepositoy extends JpaRepository<Vendedor, Long> {
}
