package com.franciscomontalvao.relatoriodevendas.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record VendedorRequestDTO(

		@NotBlank(message = "Nome nao pode estar vazio")
		@Size(min = 2, message = "Nome nao pode ter menos de 2 letras")
		String nome,

		@NotBlank(message = "Email nao pode estar vazio")
		@Email
		String email,

		@NotBlank(message = "telefone nao pode estar vazio")
		@Size(min = 11, max = 11, message = "tamanho invalido")
		String telefone
) {
}
