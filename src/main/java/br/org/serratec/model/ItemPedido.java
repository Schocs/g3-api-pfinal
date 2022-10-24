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
	private Long idItemPedido;
	
	@NotBlank(message = "Preencha a quantidade")
	@Column(nullable = false)
	private Integer quantidade;
	
	@NotBlank(message = "Preencha o preco da venda")
	@Column(nullable = false, name = "preco_venda")
	private Double precoVenda;
	
	@NotBlank(message = "Preencha o produto")
	@OneToOne
	@JoinColumn(name = "id_produto")
	private Produto idProduto;
	
	@NotBlank(message = "Preencha o pedido")
	@ManyToOne
	@JoinColumn(name = "id_pedido")
	private Pedido idPedido;

	public ItemPedido(@NotBlank(message = "Preencha a quantidade") Integer quantidade,
			@NotBlank(message = "Preencha o preco da venda") Double precoVenda,
			@NotBlank(message = "Preencha o produto") @NotBlank(message = "Preencha o produto") Produto idProduto,
			@NotBlank(message = "Preencha o pedido") @NotBlank(message = "Preencha o pedido") Pedido idPedido) {
		this.quantidade = quantidade;
		this.precoVenda = precoVenda;
		this.idProduto = idProduto;
		this.idPedido = idPedido;
	}

	public Long getIdItemPedido() {
		return idItemPedido;
	}

	public void setIdItemPedido(Long idItemPedido) {
		this.idItemPedido = idItemPedido;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(@NotBlank(message = "Preencha a quantidade") Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(@NotBlank(message = "Preencha o preco da venda") Double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public Produto getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(@NotBlank(message = "Preencha o produto") Produto idProduto) {
		this.idProduto = idProduto;
	}

	public Pedido getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(@NotBlank(message = "Preencha o pedido") Pedido idPedido) {
		this.idPedido = idPedido;
	}
	
}