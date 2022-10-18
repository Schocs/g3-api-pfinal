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
	
	private Integer quantidade;
	
	/*
	@Column(name = "preco_itens")
	private Double precoItens = produto.preco * quantidade;
	*/
	//Quantidade precisar virar double no cálculo
	//Preço total da venda precisa ser calculado a partir de todos os precoItens da venda
	
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

	public ItemPedido() {
		// TODO Auto-generated constructor stub
	}
	
	public ItemPedido(Integer quantidade, @NotBlank(message = "Preencha o produto") Produto produto,
			@NotBlank(message = "Preencha o pedido") Pedido pedido) {
		this.quantidade = quantidade;
		this.produto = produto;
		this.pedido = pedido;
	}


	public Long getIdItemPedido() {
		return idItensPedido;
	}

	public Integer getQuantidade() {
		return quantidade;
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
