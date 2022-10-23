package br.org.serratec.dto;

import br.org.serratec.model.Cliente;

public class ClienteInserirDTO {

	private String nome;
	private String username;
	private String email;

	public ClienteInserirDTO() {
	}

	public ClienteInserirDTO(Cliente cliente) {
		this.nome = cliente.getNomeCompleto();
		this.username = cliente.getNomeUsuario();
		this.email = cliente.getEmail();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
