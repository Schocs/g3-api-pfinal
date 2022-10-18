package br.org.serratec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.serratec.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	Cliente findByIdCliente(Long id);
	Cliente findByEmail(String email);
	Optional<Cliente> findByNomeUsuario(String nomeUsuario);
}
