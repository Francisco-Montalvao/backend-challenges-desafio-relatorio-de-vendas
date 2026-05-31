package com.franciscomontalvao.relatoriodevendas.service;


import com.franciscomontalvao.relatoriodevendas.dto.request.VendedorAtualizarDadosDTO;
import com.franciscomontalvao.relatoriodevendas.dto.request.VendedorRequestDTO;
import com.franciscomontalvao.relatoriodevendas.dto.response.VendedorResponseDTO;
import com.franciscomontalvao.relatoriodevendas.exception.RecursoNaoEncontradoException;
import com.franciscomontalvao.relatoriodevendas.mapper.VendedorMapper;
import com.franciscomontalvao.relatoriodevendas.repository.VendedorRepositoy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
public class VendedorService {
	private final VendedorRepositoy repository;
	private final VendedorMapper mapper;

	public VendedorService(VendedorRepositoy repositoy, VendedorMapper mapper){
		this.repository = repositoy;
		this.mapper = mapper;
	}


	@Transactional
	public VendedorResponseDTO cadastrarVendedor(VendedorRequestDTO dto){
		var response = mapper.toEntity(dto);
		response = repository.save(response);
		return mapper.toDto(response);
	}

	public List<VendedorResponseDTO> listarTodosVendedores(){
		return repository.findAll()
				.stream()
				.map(mapper::toDto)
				.toList();
	}


	public VendedorResponseDTO listarVendedorPorId (Long id){
		var response = repository.findById(id).orElseThrow(
				()-> new RecursoNaoEncontradoException("Vendedor com id " + id + " nao cadastrado", HttpStatus.NOT_FOUND)
		);

		return mapper.toDto(response);
	}


	@Transactional
	public VendedorResponseDTO atualizarDadosVendedor (Long id, VendedorAtualizarDadosDTO dto){
		var response = repository.findById(id).orElseThrow(
				()-> new RecursoNaoEncontradoException("Vendedor com id " + id + " nao cadastrado", HttpStatus.NOT_FOUND)
		);

		var vendedorAtualizado = mapper.atualizarVendedor(response, dto);

		vendedorAtualizado = repository.save(vendedorAtualizado);

		return mapper.toDto(vendedorAtualizado);
	}

	@Transactional
	public void deletarVendedor(Long id){
		var response = repository.findById(id).orElseThrow(
				()-> new RecursoNaoEncontradoException("Vendedor com id " + id + " nao cadastrado", HttpStatus.NOT_FOUND)
		);
		// falta validar
		repository.delete(response);
	}
}
