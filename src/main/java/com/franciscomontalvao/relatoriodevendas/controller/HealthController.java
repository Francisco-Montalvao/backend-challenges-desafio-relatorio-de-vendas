package com.franciscomontalvao.relatoriodevendas.controller;


import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/health")
public class HealthController {

	@GetMapping
	public ResponseEntity<String> verificaServico (){

		return ResponseEntity.ok().build();
	}
}
