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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "item_pedido")
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_item_pedido")
	private Long idItemPedido;

	@NotNull
	@Column(nullable = false)
	private Integer quantidade;

	@NotNull
	@Column(nullable = false, name = "preco_venda")
	private Double precoVenda;

	@NotNull
	@OneToOne
	@JoinColumn(name = "id_produto")
	private Produto idProduto;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_pedido")
	private Pedido idPedido;

	public ItemPedido() {
		// TODO Auto-generated constructor stub
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