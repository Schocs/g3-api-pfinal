package br.org.serratec.dto;

import java.time.LocalDate;

import br.org.serratec.model.Cliente;
import br.org.serratec.model.Pedido;

public class PedidoDTO {

	private Long idPedido;
	private LocalDate dataPedido;
	private Cliente cliente;
	private String status;

	public PedidoDTO() {

	}

	public PedidoDTO(Pedido pedido) {
		this.idPedido = pedido.getIdPedido();
		this.dataPedido = pedido.getDataPedido();
		this.cliente = pedido.getCliente();
		this.status = pedido.getStatus();
	}

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
