package br.org.serratec.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")
	private Long idCategoria;
	private String nome;
	private String descricao;

//	@JsonManagedReference
//	@OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER)
//	private Set<Produto> Produto = new HashSet<>();

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
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
//
//	public Set<Produto> getProduto() {
//		return Produto;
//	}
//
//	public void setProduto(Set<Produto> produto) {
//		Produto = produto;
//	}

}