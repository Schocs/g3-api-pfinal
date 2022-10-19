package br.org.serratec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.model.Pedido;
import br.org.serratec.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	

	public Optional<List<Pedido>> listarTodos() {
		Optional<List<Pedido>> pedido = Optional.ofNullable(pedidoRepository.findAll());
		return pedido;
	}
	

	public Optional<Pedido> listar(Long id) {

		Optional<Pedido> pedido = pedidoRepository.findById(id);

		return pedido;
	}
	

	public boolean cadastrarPedido(Pedido pedido) {

		try {
			pedidoRepository.save(pedido);
			return true;

		} catch (Exception e) {
			return false;
		}
	}
	

	public Optional<Pedido> atualizar(Long id, Pedido dadosPedido) {

		Optional<Pedido> pedido = pedidoRepository.findById(id);

		if (!pedido.isPresent()) {
			return pedido;
		}
		dadosPedido.setId(id);
		pedidoRepository.save(dadosPedido);
		return pedido;
	}
	

	public boolean deletar(Long id) {

		if (!pedidoRepository.existsById(id)) {
			return false;
		}
		pedidoRepository.deleteById(id);
		return true;
	}
}