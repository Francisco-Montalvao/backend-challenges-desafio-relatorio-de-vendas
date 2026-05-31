package com.franciscomontalvao.relatoriodevendas.service;


import com.franciscomontalvao.relatoriodevendas.dto.request.VendaAtualizarRequestoDTO;
import com.franciscomontalvao.relatoriodevendas.dto.request.VendaRequestDTO;
import com.franciscomontalvao.relatoriodevendas.dto.response.VendaResponseDTO;
import com.franciscomontalvao.relatoriodevendas.exception.RecursoNaoEncontradoException;
import com.franciscomontalvao.relatoriodevendas.mapper.VendaMapper;
import com.franciscomontalvao.relatoriodevendas.model.Venda;
import com.franciscomontalvao.relatoriodevendas.repository.VendaRepository;
import com.franciscomontalvao.relatoriodevendas.repository.VendedorRepositoy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional(readOnly = true)
@Service
public class VendaService {
	private final VendaRepository repository;
	private final VendaMapper mapper;
	private final VendedorRepositoy vendedorRepository;


	public VendaService(VendaRepository repository, VendaMapper mapper, VendedorRepositoy vendedorRepository) {
		this.repository = repository;
		this.mapper = mapper;
		this.vendedorRepository = vendedorRepository;
	}

	@Transactional
	public VendaResponseDTO cadastrarVenda(VendaRequestDTO dto) {
		var vendedor = vendedorRepository.findById(dto.vendedorId()).orElseThrow(
				() -> new RecursoNaoEncontradoException("Vendedor com id " + dto.vendedorId() + " nao cadastrado", HttpStatus.NOT_FOUND)
		);
		var response = mapper.toEntity(dto, vendedor);

		response = repository.save(response);

		return mapper.toDto(response);
	}

	public List<VendaResponseDTO> listarTodasAsVendas() {
		return repository.findAll()
				.stream()
				.map(mapper::toDto)
				.toList();
	}

	public VendaResponseDTO listarPorId(Long id) {
		var venda = buscarPorId(id);

		return mapper.toDto(venda);
	}

	public VendaResponseDTO atualizarVenda(Long id, VendaAtualizarRequestoDTO dto) {
		var venda = buscarPorId(id);

		var vendaAtualizada = repository.save(mapper.atualizarVendaFromDto(venda, dto));

		return mapper.toDto(vendaAtualizada);
	}

	public void deletarVenda(Long id) {
		var venda = buscarPorId(id);

		repository.deleteById(venda.getId());
	}

	private Venda buscarPorId(Long id) {

		return repository.findById(id).orElseThrow(
				() -> new RecursoNaoEncontradoException("venda com id " + id + " nao cadastrado", HttpStatus.NOT_FOUND)
		);

	}

}
