package br.org.serratec.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//EM CLIENTE AINDA FALTA COLOCAR UMA VARIÁVEL PARA ENDEREÇO. ACHEI QUE SERIA EMBED, MAS 
//AGORA ME PARECE QUE PODEMOS DISCUTIR AINDA COMO FAREMOS ISSO. UM CLIENTE PODE TER MAIS DE
//UM ENDEREÇO, POR EXEMPLO. E ISSO VAI AFETAR NO DTO TB
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Long idCliente;

	@Email(message = "Preencha o campo email corretamente.")
	private String email;

	@NotBlank(message = "O campo nome de usuário não pode ser deixado em branco")
	@Column(name = "nome_usuario", nullable = false)
	@Size(max = 20, message = "O nome de usuário não pode ser maior do que 20 caracteres")
	private String nomeUsuario;

	@NotBlank(message = "O campo nome completo não pode ser deixado em branco")
	@Column(name = "nome_completo", nullable = false)
	@Size(max = 60, message = "O nome não pode ser maior do que 60 caracteres")
	private String nomeCompleto;

	@NotBlank(message = "O campo senha não pode ser deixado em branco")
	private String senha;

	@NotBlank(message = "O campo CPF não pode ser deixado em branco. Não utilize pontos e máscara.")
	private String cpf;

	private String telefone;

	@Column(name = "data_Nascimento")
	private LocalDate dataNascimento;

	@OneToOne
	@JoinColumn(name = "id_endereco")
	private Endereco endereco;

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", email=" + email + ", nomeUsuario=" + nomeUsuario
				+ ", nomeCompleto=" + nomeCompleto + ", telefone=" + telefone + "]";
	}

}
