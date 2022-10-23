package br.org.serratec.dto;

import br.org.serratec.model.Cliente;

//ESSE MODELO É SÓ PRA CONSULTAS, ACHO QUE SERÁ PRECISO UM DIFERENTE PRA CADA SITUAÇÃO
public class ClienteDTO {

	private String nome;
	private String username;
	private Long id;
	private String email;
	private String telefone;

	public ClienteDTO() {
	}

	public ClienteDTO(Cliente cliente) {
		this.nome = cliente.getNomeCompleto();
		this.username = cliente.getNomeUsuario();
		this.id = cliente.getIdCliente();
		this.email = cliente.getEmail();
		this.telefone = cliente.getTelefone();
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Olá, " + nome + "! Você se cadastrou com sucesso!\n Seu username é " + username + ", cadastrado com o email " + email + " e telefone "
				+ telefone + ".\n Obrigado por se cadastrar em nossa loja!";
	}
	
	public String toStringRemover() {
		return "Olá, " + nome + "! Seu cadastro foi removido com sucesso!\n.\n Obrigado por utilizar nossa loja!\n Se puder, deixe-nos um feedback através de nosso email grupo3serratec2022.2@gmail.com. Obrigado e volte sempre!";
	}

	public String toStringAtualizar() {
		return "Olá, " + nome + "! Seu cadastro foi atualizado com sucesso!\n.\n Obrigado por utilizar nossa loja!\n Se puder, deixe-nos um feedback através de nosso email grupo3serratec2022.2@gmail.com. Obrigado e volte sempre!";
	}

}
