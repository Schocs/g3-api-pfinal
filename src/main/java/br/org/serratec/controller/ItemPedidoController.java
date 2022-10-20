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

@RestController
@RequestMapping("/ItemPedido")
public class ItemPedidoController {
	@Autowired
	ItemPedidoService itemPedidoService;
	
	@GetMapping("/listar/todos")
	public ResponseEntity<List<ItemPedido>> listarTodos() {
		
		Optional<List<ItemPedido>> itemPedido = itemPedidoService.listarTodos();
		
		if (!itemPedido.isPresent()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(itemPedido.get());
	}
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<ItemPedido> buscarPorId(@PathVariable Long id) {
		Optional<ItemPedido> itemPedido = itemPedidoService.buscarPorId(id);
		
		if (!itemPedido.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(itemPedido.get());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Void> cadastrar(@Valid @RequestBody ItemPedido dadosItemPedido) {
		
		Boolean foiCadastrado = itemPedidoService.cadastrar(dadosItemPedido);
		
		if (foiCadastrado) {
			return ResponseEntity.status(201).build();
		}
		
		return ResponseEntity.internalServerError().build();
		
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<ItemPedido> atualizar(@PathVariable Long id, @Valid @RequestBody ItemPedido dadosItemPedido) {
		Optional<ItemPedido> itemPedido = itemPedidoService.atualizar(id, dadosItemPedido);
		
		if (!itemPedido.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(itemPedido.get());
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<String> deletar(@PathVariable Long id) {
		
		Boolean foiDeletado = itemPedidoService.deletar(id);
		
		if (!foiDeletado) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok("Usúario Deletado com sucesso");
	}
	
}
