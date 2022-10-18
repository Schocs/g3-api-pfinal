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
@Table(name = "itens_pedido")
public class ItemPedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_itens_pedido")
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
	@JoinColumn(name = "id_produto")
	private Produto produto;
	
	//Precisa de FK?
	@NotBlank(message = "Preencha o pedido")
	@ManyToOne
	@JoinColumn(name = "id_Pedido")
	private Pedido pedido;
}
