package br.org.serratec.dto;

import java.time.LocalDate;

import br.org.serratec.model.Categoria;
import br.org.serratec.model.Produto;

public class ProdutoDTO {

	private Long idproduto;
	private String nome;
	private String descricao;
	private Integer estoque;
	private LocalDate dataCadastro;
	private Double valor;
	private Categoria categoria;

	public ProdutoDTO() {

	}

	public ProdutoDTO(Produto produto) {
		this.idproduto = produto.getIdproduto();
		this.nome = produto.getNome();
		this.descricao = produto.getDescricao();
		this.estoque = produto.getEstoque();
		this.dataCadastro = produto.getDataCadastro();
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

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
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
