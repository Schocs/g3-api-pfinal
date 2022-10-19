package br.org.serratec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import antlr.collections.List;
import br.org.serratec.model.ItemPedido;

@RestController
@RequestMapping("/ItemPedido")
public class ItemPedidoController {
	@Autowired
	private ItemPedido itemPedidoRepository;

	@GetMapping
	public List listar(){
	return itemPedidoRepository.findAll();
	}
}
