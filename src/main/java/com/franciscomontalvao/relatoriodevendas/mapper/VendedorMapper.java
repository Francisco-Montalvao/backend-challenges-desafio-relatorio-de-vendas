package com.franciscomontalvao.relatoriodevendas.mapper;


import com.franciscomontalvao.relatoriodevendas.dto.request.VendedorAtualizarDadosDTO;
import com.franciscomontalvao.relatoriodevendas.dto.request.VendedorRequestDTO;
import com.franciscomontalvao.relatoriodevendas.dto.response.VendedorResponseDTO;
import com.franciscomontalvao.relatoriodevendas.model.Vendedor;
import org.springframework.stereotype.Component;

@Component
public class VendedorMapper {

	public Vendedor toEntity(VendedorRequestDTO dto){
		Vendedor vendedor = new Vendedor();
		vendedor.setNome(dto.nome());
		vendedor.setEmail(dto.email());
		vendedor.setTelefone(dto.telefone());

		return vendedor;
	}

	public VendedorResponseDTO toDto(Vendedor vendedor){
		return new VendedorResponseDTO(
				vendedor.getId(),
				vendedor.getNome(),
				vendedor.getEmail(),
				vendedor.getTelefone()
		);
	}


	public Vendedor atualizarVendedor(Vendedor vendedor, VendedorAtualizarDadosDTO dto) {
		vendedor.setEmail(dto.email());
		vendedor.setTelefone(dto.telefone());
		return vendedor;
	}
}
