package br.org.serratec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_item_pedido")
	private Long idItensPedido;
	
	@NotBlank(message = "Preencha a quantidade")
	@Column(nullable = false)
	private Integer quantidade;
	
	@NotBlank(message = "Preencha o preco da venda")
	@Column(nullable = false, name = "preco_venda")
	private Double precoVenda;
	
	//Precisa de FK?
	@NotBlank(message = "Preencha o produto")
	@OneToOne
	@JoinColumn(name = "produto")
	private Produto produto;
	
	//Precisa de FK?
	@NotBlank(message = "Preencha o pedido")
	@ManyToOne
	@JoinColumn(name = "Pedido")
	private Pedido pedido;
	
	
	public ItemPedido(@NotBlank(message = "Preencha a quantidade") Integer quantidade,
			@NotBlank(message = "Preencha o preco da venda") Double precoVenda,
			@NotBlank(message = "Preencha o produto") Produto produto,
			@NotBlank(message = "Preencha o pedido") Pedido pedido) {
		this.quantidade = quantidade;
		this.precoVenda = precoVenda;
		this.produto = produto;
		this.pedido = pedido;
	}

	public Long getIdItemPedido() {
		return idItensPedido;
	}

	public Integer getQuantidade() {
		return quantidade;
	}
	
	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
}
