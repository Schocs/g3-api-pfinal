package br.org.serratec.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import antlr.collections.List;

@Entity
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_produto")
	private Long idProduto;

	@NotBlank(message = "Prencher nome do produto")
	@Column(name = "nome", nullable = false, length = 40)
	private String nome;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "qtd_estoque")
	private Integer estoque;

	@Column(name = "data_cadastro")
	@NotNull(message = "Verifique o preenchimento de dataCadastro")
	private LocalDate dataCadastro;

	@Column(name = "valor_unitario")
	private Double valor;

	@ManyToOne
	@JoinColumn(name = "id_categoria")
	@NotNull(message = "Todo produto deve ter uma categoria. Verifique preenchimento.")
	private Categoria categoria;

	public Long getIdproduto() {
		return idProduto;
	}

	public void setIdproduto(Long idProduto) {
		this.idProduto = idProduto;
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

	public List findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}