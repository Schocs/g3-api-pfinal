package br.org.serratec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.model.ItemPedido;
import br.org.serratec.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {
	@Autowired
	private ItemPedidoRepository itemPedidoRespository;
	
	public Optional<ItemPedido> buscarPorId(Long id) {
		Optional<ItemPedido> itemPedido = itemPedidoRespository.findById(id);
		
		return itemPedido;
	}
	
	//Buscar por idPedido?
	
	public Optional<List<ItemPedido>> listarTodos() {
		Optional<List<ItemPedido>> itemPedido = Optional.ofNullable(itemPedidoRespository.findAll());
		
		return itemPedido;
	}
	
	public Boolean cadastrar(ItemPedido dadosItemPedido) {
		
		try {
			itemPedidoRespository.save(dadosItemPedido);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Optional<ItemPedido> atualizar(Long id, ItemPedido dadosItemPedido) {
		Optional<ItemPedido> itemPedido = itemPedidoRespository.findById(id);
		
		if (!itemPedido.isPresent()) {
			return itemPedido;
		}
		
		if (dadosItemPedido.getIdItemPedido() == null) {
			dadosItemPedido.setIdItemPedido(id);
		}
		
		
		itemPedidoRespository.save(dadosItemPedido);
		
		return itemPedido;
	}
	
	public Boolean deletar(Long id) {
		
		if (!itemPedidoRespository.existsById(id)) {
			return false;
		}
		
		itemPedidoRespository.deleteById(id);
		return true;
	}
	
}
