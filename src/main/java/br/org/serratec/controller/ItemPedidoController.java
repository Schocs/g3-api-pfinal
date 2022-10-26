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

import br.org.serratec.model.ItemPedido;
import br.org.serratec.service.ItemPedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/itenspedidos")
public class ItemPedidoController {
	@Autowired
	ItemPedidoService itemPedidoService;

	@GetMapping("/todos")
	@ApiOperation(value = "Buscar todos os itens de pedido cadastrados no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna a lista de todos os itens de pedido cadastrados no sistema"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para esse recurso"),
			@ApiResponse(responseCode = "404", description = "Itens de pedido não encontrados"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação") })
	public ResponseEntity<List<ItemPedido>> listarTodos() {

		Optional<List<ItemPedido>> itemPedido = itemPedidoService.listarTodos();

		if (!itemPedido.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(itemPedido.get());
	}

	@GetMapping("/buscar/{id}")
	@ApiOperation(value = "Buscar o item de pedido pelo id cadastrado no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna o item de pedido cadastrado no sistema"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para esse recurso"),
			@ApiResponse(responseCode = "404", description = "Item de pedido não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação") })
	public ResponseEntity<ItemPedido> buscarPorId(@PathVariable Long id) {
		Optional<ItemPedido> itemPedido = itemPedidoService.buscarPorId(id);

		if (!itemPedido.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(itemPedido.get());
	}

	@PostMapping("/cadastrar")
	@ApiOperation(value = "Cadastra um novo item de pedido no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cadastra o novo item de pedido no sistema"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para esse recurso"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação") })
	public ResponseEntity<Void> cadastrar(@Valid @RequestBody ItemPedido dadosItemPedido) {

		Boolean foiCadastrado = itemPedidoService.cadastrar(dadosItemPedido);

		if (foiCadastrado) {
			return ResponseEntity.status(201).build();
		}

		return ResponseEntity.badRequest().build();

	}

	@PutMapping("/atualizar/{id}")
	@ApiOperation(value = "Atualizar um item de pedido pelo id cadastrado no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Atualiza o item de pedido cadastrado no sistema"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para esse recurso"),
			@ApiResponse(responseCode = "404", description = "Item de pedido não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação") })
	public ResponseEntity<ItemPedido> atualizar(@PathVariable Long id, @Valid @RequestBody ItemPedido dadosItemPedido) {
		Optional<ItemPedido> itemPedido = itemPedidoService.atualizar(id, dadosItemPedido);

		if (!itemPedido.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(itemPedido.get());
	}

	@DeleteMapping("/deletar/{id}")
	@ApiOperation(value = "Deletar um item de pedido pelo id cadastrado no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Deleta o item de pedido cadastrado no sistema"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para esse recurso"),
			@ApiResponse(responseCode = "404", description = "Item de pedido não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação") })
	public ResponseEntity<String> deletar(@PathVariable Long id) {

		Boolean foiDeletado = itemPedidoService.deletar(id);

		if (!foiDeletado) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok("Usúario Deletado com sucesso");
	}

}
