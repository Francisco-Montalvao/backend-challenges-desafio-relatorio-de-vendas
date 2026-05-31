package com.franciscomontalvao.relatoriodevendas.mapper;


import com.franciscomontalvao.relatoriodevendas.dto.request.VendaAtualizarRequestoDTO;
import com.franciscomontalvao.relatoriodevendas.dto.request.VendaRequestDTO;
import com.franciscomontalvao.relatoriodevendas.dto.response.VendaResponseDTO;
import com.franciscomontalvao.relatoriodevendas.model.Venda;
import com.franciscomontalvao.relatoriodevendas.model.Vendedor;
import org.springframework.stereotype.Component;


@Component
public class VendaMapper {

	public Venda toEntity(VendaRequestDTO dto, Vendedor vendedor){
		Venda venda = new Venda();
		venda.setVendedor(vendedor);
		venda.setValorTotal(dto.valorTotal());
		venda.setData(dto.data());

		return venda;
	}

	public VendaResponseDTO toDto (Venda venda){
		return new VendaResponseDTO(
				venda.getId(),
				venda.getVendedor().getId(),
				venda.getValorTotal(),
				venda.getData()
		);
	}


	public Venda atualizarVendaFromDto(Venda venda, VendaAtualizarRequestoDTO dto) {

		venda.setValorTotal(dto.valorTotal());

		return venda;
	}
}
