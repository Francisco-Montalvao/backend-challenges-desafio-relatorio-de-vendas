package com.franciscomontalvao.relatoriodevendas.controller;


import com.franciscomontalvao.relatoriodevendas.dto.request.VendedorAtualizarDadosDTO;
import com.franciscomontalvao.relatoriodevendas.dto.request.VendedorRequestDTO;
import com.franciscomontalvao.relatoriodevendas.dto.response.VendedorResponseDTO;
import com.franciscomontalvao.relatoriodevendas.service.VendedorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/vendedores")
public class VendedorController {
	private final VendedorService service;

	public VendedorController(VendedorService service){
		this.service = service;

	}

	@PostMapping
	public ResponseEntity<VendedorResponseDTO> cadastrarVendedor (@RequestBody @Valid VendedorRequestDTO dto, UriComponentsBuilder uriComponentsBuilder){
		var response = service.cadastrarVendedor(dto);

		URI uri = uriComponentsBuilder
				.path("api/v1/vendedores")
				.buildAndExpand(response.id())
				.toUri();

		return ResponseEntity.created(uri).body(response);
	}

	@GetMapping
	public ResponseEntity<List<VendedorResponseDTO>> listarTodos(){

		return ResponseEntity.ok(service.listarTodosVendedores());
	}

	@GetMapping("/{id}")
	public ResponseEntity<VendedorResponseDTO> listarPorId(@PathVariable("id") Long id){
		return ResponseEntity.ok(service.listarVendedorPorId(id));
	}


	@PutMapping("/{id}")
	public ResponseEntity<VendedorResponseDTO> atualizarVendedor(@PathVariable Long id, @RequestBody @Valid VendedorAtualizarDadosDTO dto){
		var response = service.atualizarDadosVendedor(id, dto);

		return ResponseEntity.ok().body(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> ApagarVendedor(@PathVariable("id") Long id){
		service.deletarVendedor(id);

		return ResponseEntity.noContent().build();
	}
}
