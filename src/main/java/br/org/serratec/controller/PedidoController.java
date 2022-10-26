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

import br.org.serratec.model.Pedido;
import br.org.serratec.service.PedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping("/todos")
	@ApiOperation(value = "Buscar todos os pedidos cadastrados no sistema")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Retorna a lista de todos os pedidos cadastrados no sistema"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para esse recurso"),
			@ApiResponse(responseCode = "404", description = "Pedidos não encontrados"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação") })
	public ResponseEntity<List<Pedido>> listarTodos() {
		Optional<List<Pedido>> pedido = pedidoService.listarTodos();

		if (pedido.isPresent()) {
			return ResponseEntity.ok(pedido.get());
		}

		return ResponseEntity.notFound().build();
	}

	@GetMapping("/buscar/{id}")
	@ApiOperation(value = "Buscar o pedido pelo id cadastrado no sistema")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Retorna o pedido cadastrado no sistema"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para esse recurso"),
			@ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação") })
	public ResponseEntity<Pedido> listar(@PathVariable Long id) {

		Optional<Pedido> pedido = pedidoService.listar(id);

		if (pedido.isPresent()) {
			return ResponseEntity.ok(pedido.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping("/cadastrar")
	@ApiOperation(value = "Cadastra um novo pedido no sistema")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Cadastra o novo pedido no sistema"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para esse recurso"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação") })
	public ResponseEntity<Void> cadastrarPedido(@Valid @RequestBody Pedido pedido) {

		boolean foiCadastrado = pedidoService.cadastrarPedido(pedido);
		if (foiCadastrado) {
			return ResponseEntity.status(201).build();
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping("/atualizar/{id}")
	@ApiOperation(value = "Atualizar um pedido pelo id cadastrado no sistema")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Atualiza o pedido cadastrado no sistema"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para esse recurso"),
			@ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação") })
	public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @Valid @RequestBody Pedido dadosPedido) {

		Optional<Pedido> pedido = pedidoService.atualizar(id, dadosPedido);

		if (!pedido.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pedido.get());
	}

	@DeleteMapping("/deletar/{id}")
	@ApiOperation(value = "Deletar um pedido pelo id cadastrado no sistema")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deleta o pedido cadastrado no sistema"),
			@ApiResponse(responseCode = "401", description = "Erro de autenticação"),
			@ApiResponse(responseCode = "403", description = "Você não tem permissão para esse recurso"),
			@ApiResponse(responseCode = "404", description = "Pedido não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro na aplicação") })
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		boolean foiDeletado = pedidoService.deletar(id);

		if (!foiDeletado) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.noContent().build();
	}

}