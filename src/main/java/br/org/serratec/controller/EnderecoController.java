package br.org.serratec.controller;


import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.dto.EnderecoDTO;
import br.org.serratec.exception.CepException;
import br.org.serratec.model.Endereco;
import br.org.serratec.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping
	public ResponseEntity<List<EnderecoDTO>> listar(){
		return ResponseEntity.ok(enderecoService.listar());
	}
	
	@GetMapping("/{cep}")
	public ResponseEntity<EnderecoDTO> buscar(@PathVariable String cep) {
		if(enderecoService.buscar(cep) != null) {
			return ResponseEntity.ok(enderecoService.buscar(cep));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/cadastar")
	public ResponseEntity<Object> cadastrarEndereco(@Valid @RequestBody Endereco endereco) {
		try {
			EnderecoDTO enderecoDTO = enderecoService.inserir(endereco);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{cep}")
					.buildAndExpand(enderecoDTO.getCep()).toUri();
			return ResponseEntity.created(uri).body(enderecoDTO);

		} catch (CepException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Void> removerEndereco(@PathVariable Long id) {
		boolean foiDeletado = enderecoService.deletar(id);
		if(foiDeletado == false) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.noContent().build();
		}	
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<Object> atualizarEndereco(@PathVariable Long id, @Valid @RequestBody Endereco endereco) {
		try {
		EnderecoDTO enderecoDTO = enderecoService.atualizar(id, endereco);
		return ResponseEntity.ok(enderecoDTO);
		} catch (CepException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
		
	}
}
