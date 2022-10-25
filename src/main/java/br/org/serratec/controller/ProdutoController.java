package br.org.serratec.controller;

import java.util.List;
import java.util.Optional;

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

import br.org.serratec.model.Produto;
import br.org.serratec.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping("/todos")
	@ApiOperation(value = "Buscar todos os produtos cadastrados no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna a lista de todos os produtos cadrastrados no sistema"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para esse recurso"),
			@ApiResponse(responseCode = "404", description = "Produtos não encontrados"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação") })
	public ResponseEntity<List<Produto>> listarTodos() {
		Optional<List<Produto>> produto = produtoService.listarTodosService();

		if (produto.isPresent()) {
			return ResponseEntity.ok(produto.get());
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/buscar/{id}")
	@ApiOperation(value = "Buscar o produto pelo id cadastrado no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna o produto cadastrado no sistema"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para esse recurso"),
			@ApiResponse(responseCode = "404", description = "Produto não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação")
	})
	public ResponseEntity<Produto> listar(@PathVariable Long id) {
		Optional<Produto> produto = produtoService.listar(id);

		if (produto.isPresent()) {
			return ResponseEntity.ok(produto.get());
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("/cadastrar")
	@ApiOperation(value = "Cadastra um novo produto no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cadastra o novo produto no sistema"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para esse recurso"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação")
	})
	public ResponseEntity<Void> cadastrarPedido(@Valid @RequestBody Produto produto) {

		boolean foiCadastrado = produtoService.cadastrarProduto(produto);
		if (foiCadastrado) {
			return ResponseEntity.status(201).build();
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/atualizar/{id}")
	@ApiOperation(value = "Atualizar um produto pelo id cadastrado no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Atualiza o produto cadastrado no sistema"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para esse recurso"),
			@ApiResponse(responseCode = "404", description = "Produto não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação")
	})
	public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody Produto dadosProduto) {

		Optional<Produto> produto = produtoService.atualizarService(id, dadosProduto);

		if (!produto.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(produto.get());

	}

	@DeleteMapping("/deletar/{id}")
	@ApiOperation(value = "Deletar um produto pelo id cadastrado no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Deleta o produto cadastrado no sistema"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para esse recurso"),
			@ApiResponse(responseCode = "404", description = "Produto não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação")
	})
	public ResponseEntity<Void> deletar(@PathVariable Long id) {

		boolean foiDeletado = produtoService.deletar(id);
		if (!foiDeletado) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}

}
