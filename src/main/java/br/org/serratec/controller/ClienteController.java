package br.org.serratec.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.dto.ClienteDTO;
import br.org.serratec.dto.ClienteInserirDTO;
import br.org.serratec.exception.UserException;
import br.org.serratec.model.Cliente;
import br.org.serratec.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
//	@Autowired
//	private ClienteRepository clienteRepository;
	
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> listar(){
		return ResponseEntity.ok(clienteService.listarClientes());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ClienteDTO> buscarCliente(@PathVariable Long id) {
		if(clienteService.buscarCliente(id)!=null) {
			return ResponseEntity.ok(clienteService.buscarCliente(id));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Object> cadastrarCliente(@Valid @RequestBody Cliente cliente){
		try {
			ClienteInserirDTO clienteInserirDTO = clienteService.cadastrarCliente(cliente);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
					.buildAndExpand(clienteInserirDTO.getUsername()).toUri();
			return ResponseEntity.created(uri).body(clienteInserirDTO);

		} catch (UserException e) {
			return ResponseEntity.unprocessableEntity().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/remover/{id}")
	public ResponseEntity<Void> removerCadastro(@PathVariable Long id) {
		boolean foiDeletado = clienteService.removerCadastro(id);
		if(foiDeletado == false) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<Cliente> atualizarCadastro(@PathVariable Long id, @Valid @RequestBody Cliente dadosCliente) {
		Optional<Cliente> cliente = clienteService.atualizar(id, dadosCliente);
		if (!cliente.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cliente.get());
	}
}
