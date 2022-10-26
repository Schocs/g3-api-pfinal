package br.org.serratec.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.org.serratec.dto.EnderecoDTO;

@Entity
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_endereco")
	private Long idEndereco;

	private String cep;

	@Column(name = "rua")
	private String logradouro;

	private String bairro;

	@Column(name = "cidade")
	private String localidade;

	private String complemento;

	@Column(name = "estado")
	private String uf;

	public Endereco() {
		// TODO Auto-generated constructor stub
	}

	public Endereco(EnderecoDTO enderecoDTO) {
		this.cep = enderecoDTO.getCep();
		this.logradouro = enderecoDTO.getLogradouro();
		this.bairro = enderecoDTO.getBairro();
		this.localidade = enderecoDTO.getLocalidade();
		this.complemento = enderecoDTO.getComplemento();
		this.uf = enderecoDTO.getUf();
	}

	public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

}
