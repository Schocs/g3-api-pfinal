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
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	
	@Autowired
	private EnderecoService enderecoService;
	
	@GetMapping("/todos")
	@ApiOperation(value = "Buscar todos os endereços cadastrados no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna a lista de todos os endereços cadastrados no sistema"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para esse recurso"),
			@ApiResponse(responseCode = "404", description = "Endereços não encontrados"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação")
	})
	public ResponseEntity<List<EnderecoDTO>> listar(){
		return ResponseEntity.ok(enderecoService.listar());
	}
	
	@GetMapping("/{cep}")
	@ApiOperation(value = "Buscar o endereço pelo cep cadastrado no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna o endereço cadastrado no sistema"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para esse recurso"),
			@ApiResponse(responseCode = "404", description = "Endereço não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação")
	})
	public ResponseEntity<EnderecoDTO> buscar(@PathVariable String cep) {
		if(enderecoService.buscar(cep) != null) {
			return ResponseEntity.ok(enderecoService.buscar(cep));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/cadastrar")
	@ApiOperation(value = "Cadastra um novo endereço no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cadastra o novo endereço no sistema"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para esse recurso"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação")
	})
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
	@ApiOperation(value = "Deletar um endereço pelo id cadastrado no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Deleta o endereço cadastrado no sistema"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para esse recurso"),
			@ApiResponse(responseCode = "404", description = "Endereço não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação")
	})
	public ResponseEntity<Void> removerEndereco(@PathVariable Long id) {
		boolean foiDeletado = enderecoService.deletar(id);
		if(foiDeletado == false) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.noContent().build();
		}	
	}
	
	@PutMapping("/atualizar/{id}")
	@ApiOperation(value = "Atualizar um endereço pelo id cadastrado no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Atualiza o endereço cadastrado no sistema"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para esse recurso"),
			@ApiResponse(responseCode = "404", description = "Endereço não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação")
	})
	public ResponseEntity<Object> atualizarEndereco(@PathVariable Long id, @Valid @RequestBody Endereco endereco) {
		try {
		EnderecoDTO enderecoDTO = enderecoService.atualizar(id, endereco);
		return ResponseEntity.ok(enderecoDTO);
		} catch (CepException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
		
	}
}
