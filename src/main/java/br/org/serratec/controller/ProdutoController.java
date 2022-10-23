package br.org.serratec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import antlr.collections.List;
import br.org.serratec.model.Produto;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private Produto produtoRepository;

	@GetMapping
	@ApiOperation(value = "Buscar todos os produtos cadastrados no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna a lista de todos os produtos cadrastrados no sistema"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para esse recurso"),
			@ApiResponse(responseCode = "404", description = "Produtos não encontrados"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação")
	})
	public List listar(){
	return produtoRepository.findAll();
	}

	}

