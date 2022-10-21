package br.org.serratec.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Long idPedido;
	
	
	@NotBlank (message = "Preencha data do pedido")
	@Column( name = "data_pedido")
	private LocalDate dataPedido;
	
	@NotBlank (message = "Preencha data da entrega")
	@Column( name = "data_entrega")
	private LocalDate dataEntrega ;
	
	@NotBlank (message = "Preencha data do envio")
	@Column( name = "data_envio")
	private LocalDate dataEnvio;
	
	@NotBlank (message = "Preencha Status")
	@Size(max = 20)
	@Column(nullable = true, length = 20)
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	
	
	
	
	
	public Pedido(Long id, @NotBlank(message = "Preencha data do pedido") LocalDate dataPedido,
			@NotBlank(message = "Preencha data da entrega") LocalDate dataEntrega,
			@NotBlank(message = "Preencha data do envio") LocalDate dataEnvio,
			@NotBlank(message = "Preencha Status") @Size(max = 20) String status, Cliente cliente) {
		super();
		this.idPedido = id;
		this.dataPedido = dataPedido;
		this.dataEntrega = dataEntrega;
		this.dataEnvio = dataEnvio;
		this.status = status;
		this.cliente = cliente;
	}
	public Long getIdPedido() {
		return this.idPedido;
	}

	public Pedido() {
		super();
	}
	
	public void setId(Long id) {
		this.idPedido = id;
	}

	public LocalDate getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(LocalDate dataPedido) {
		this.dataPedido = dataPedido;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public LocalDate getDataEnvio() {
		return dataEnvio;
	}

	public void setDataEnvio(LocalDate dataEnvio) {
		this.dataEnvio = dataEnvio;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
	
	
}
