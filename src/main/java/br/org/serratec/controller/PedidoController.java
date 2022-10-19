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


	@RestController
	@RequestMapping("/pedido")
	public class PedidoController {

		@Autowired
		private PedidoService pedidoService;
		
		@GetMapping("/todos")
		public ResponseEntity<List<Pedido>> listarTodos() {
			Optional<List<Pedido>> pedido = pedidoService.listarTodos();
			
			if(pedido.isPresent()) {
				return ResponseEntity.ok(pedido.get());
			}
			
			return ResponseEntity.notFound().build();
		}
		
		@GetMapping("/listar/{id}")

		public ResponseEntity<Pedido> listar(@PathVariable Long id) {
			
			Optional<Pedido> pedido = pedidoService.listar(id);
			
			if(pedido.isPresent()) {
				return ResponseEntity.ok(pedido.get());
			}
			
			return ResponseEntity.notFound().build();
		}
		@PostMapping("/cadastrar")
		
			public ResponseEntity<Void> cadastrarPedido(@Valid @RequestBody Pedido pedido) {
			
			boolean foiCadastrado = pedidoService.cadastrarPedido(pedido);
			if (foiCadastrado) {
				return ResponseEntity.status(201).build();
			}
			else {
				return ResponseEntity.internalServerError().build();
			}
		}
		
		
		@PutMapping("/atualizar/{id}")

		public ResponseEntity<Pedido> atualizar(@PathVariable Long id, @Valid @RequestBody Pedido dadosPedido) {
			
			Optional<Pedido> pedido = pedidoService.atualizar(id, dadosPedido);
			
			if (!pedido.isPresent()) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok(pedido.get());
		}
		
		@DeleteMapping("/deletar/{id}")

		public ResponseEntity<Void> deletar(@PathVariable Long id) {
			boolean foiDeletado = pedidoService.deletar(id);
			
			if (!foiDeletado) {
				return ResponseEntity.notFound().build();
			}
			
			return ResponseEntity.noContent().build();
		}

	}