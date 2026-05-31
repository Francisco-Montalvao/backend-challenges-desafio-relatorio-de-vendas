package com.franciscomontalvao.relatoriodevendas.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "vendedores")
public class Vendedor {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false, unique = true)
	private String telefone;

	@OneToMany(mappedBy = "vendedor")
	private List<Venda> vendasList;


}
