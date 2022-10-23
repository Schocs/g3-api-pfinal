package br.org.serratec.dto;

import java.sql.Date;

import br.org.serratec.model.Categoria;
import br.org.serratec.model.Produto;

public class ProdutoDTO {

	private Long idproduto;
	private String nome;
	private String descricao;
	private Integer estoque;
	private Date data_cadastro;
	private Double valor;
	private Categoria categoria;

	public ProdutoDTO() {

	}

	public ProdutoDTO(Produto produto) {
		this.idproduto = produto.getIdproduto();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.estoque = produto.getEstoque();
		this.data_cadastro = produto.getData_cadastro();
		this.valor = produto.getValor();
		this.categoria = produto.getCategoria();
	}

	public Long getIdproduto() {
		return idproduto;
	}

	public void setIdproduto(Long idproduto) {
		this.idproduto = idproduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
