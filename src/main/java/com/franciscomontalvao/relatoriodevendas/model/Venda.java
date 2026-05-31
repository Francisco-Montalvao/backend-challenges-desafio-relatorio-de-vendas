package com.franciscomontalvao.relatoriodevendas.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter

@Entity
@Table(name = "vedas")
public class Venda {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@ManyToOne
	@JoinColumn(name = "vendedor_id")
	private Vendedor vendedor;


	@Column(name = "valor_total", nullable = false)
	private BigDecimal valorTotal;

	@Column(nullable = false)
	private LocalDate data;

}
