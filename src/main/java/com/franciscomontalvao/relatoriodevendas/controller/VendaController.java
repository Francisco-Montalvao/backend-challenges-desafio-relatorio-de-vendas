package com.franciscomontalvao.relatoriodevendas.controller;


import com.franciscomontalvao.relatoriodevendas.dto.request.VendaAtualizarRequestoDTO;
import com.franciscomontalvao.relatoriodevendas.dto.request.VendaRequestDTO;
import com.franciscomontalvao.relatoriodevendas.dto.response.VendaResponseDTO;
import com.franciscomontalvao.relatoriodevendas.repository.VendaRepository;
import com.franciscomontalvao.relatoriodevendas.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vendas")
public class VendaController {
	private final VendaService service;

	public VendaController(VendaService service){
		this.service = service;
	}


	@PostMapping
	public ResponseEntity<VendaResponseDTO> cadadstrarVenda(@RequestBody @Valid VendaRequestDTO dto, UriComponentsBuilder uriComponentsBuilder){
		var venda = service.cadastrarVenda(dto);

		URI uri = uriComponentsBuilder
				.path("api/v1/vendas/{id}")
				.buildAndExpand(venda.id())
				.toUri();

		return ResponseEntity.created(uri).body(venda);
	}

	@GetMapping
	public ResponseEntity<List<VendaResponseDTO>> listarTodasAsvendas(){
		return ResponseEntity.ok().body(service.listarTodasAsVendas());
	}

	@GetMapping("/{id}")
	public ResponseEntity<VendaResponseDTO> listarVendaPorId(@PathVariable("id") Long id){
		return ResponseEntity.ok(service.listarPorId(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<VendaResponseDTO> atualizarVendas (@PathVariable("id") Long id, @RequestBody @Valid VendaAtualizarRequestoDTO dto){
		return ResponseEntity.ok(service.atualizarVenda(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarVenda (@PathVariable("id") Long id){
		service.deletarVenda(id);

		return ResponseEntity.noContent().build();
	}

}
