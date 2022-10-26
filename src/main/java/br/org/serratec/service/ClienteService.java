package br.org.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.org.serratec.config.MailConfig;
import br.org.serratec.dto.ClienteDTO;
import br.org.serratec.dto.ClienteInserirDTO;
import br.org.serratec.exception.CpfException;
import br.org.serratec.exception.EmailException;
import br.org.serratec.exception.UserException;
import br.org.serratec.model.Cliente;
import br.org.serratec.model.Endereco;
import br.org.serratec.repository.ClienteRepository;
import br.org.serratec.repository.EnderecoRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private MailConfig mailConfig;

	public List<ClienteDTO> listarClientes() {
		List<Cliente> clientes = clienteRepository.findAll();
		List<ClienteDTO> clientesDTO = new ArrayList<>();
		for (Cliente cliente : clientes) {
			clientesDTO.add(new ClienteDTO(cliente));
		}
		return clientesDTO;
	}

	public ClienteDTO buscarCliente(Long id) {
		ClienteDTO clienteDTO = new ClienteDTO(clienteRepository.findByIdCliente(id));
		return clienteDTO;
	}

	public ClienteInserirDTO cadastrarCliente(Cliente cliente) {
		if (clienteRepository.findByEmail(cliente.getEmail()) != null) {
			throw new EmailException("Email já cadastrado");
		}
		if (clienteRepository.findByNomeUsuario(cliente.getNomeUsuario()) != null) {
			throw new UserException("Nome de usuário já cadastrado");
		}
		if (clienteRepository.findByCpf(cliente.getCpf()) != null) {
			throw new CpfException("CPF já cadastrado");
		}
		Endereco novoEndereco = new Endereco(enderecoService.buscarCliente(cliente.getEndereco().getCep()));
		cliente.setEndereco(novoEndereco);
		enderecoRepository.save(novoEndereco);
		String senha = cliente.getSenha();
		cliente.setSenha(bCryptPasswordEncoder.encode(senha));
		clienteRepository.save(cliente);
		ClienteInserirDTO clienteInserirDTO = new ClienteInserirDTO(cliente);
		ClienteDTO clienteDTO = new ClienteDTO(cliente);
		mailConfig.sendEmail(cliente.getEmail(), "Cadastro na loja Grupo3", clienteDTO.toString());
		return clienteInserirDTO;
	}

	public boolean removerCadastro(Long id) {

		if (!clienteRepository.existsById(id)) {
			return false;
		}
		ClienteDTO clienteDTO = new ClienteDTO(clienteRepository.findByIdCliente(id));
		mailConfig.sendEmailRemover(clienteRepository.findById(id).get().getEmail(), "Remoção do cadastro",
				clienteDTO.toStringRemover());
		clienteRepository.deleteById(id);
		return true;
	}

	public Optional<Cliente> atualizar(Long id, Cliente dadosCliente) {

		Optional<Cliente> cliente = clienteRepository.findById(id);
		List<Cliente> clientesMail = new ArrayList<>();
		List<Cliente> clientesUser = new ArrayList<>();
		List<Cliente> clientesCpf = new ArrayList<>();
		if (!cliente.isPresent()) {
			return cliente;
		}
		for (Cliente cliente2 : clienteRepository.findAll()) {
			if (!cliente2.getEmail().equals(dadosCliente.getEmail())) {
				clientesMail.add(cliente2);
			}
			if (!cliente2.getNomeUsuario().equals(dadosCliente.getNomeUsuario())) {
				clientesUser.add(cliente2);
			}
			if (!cliente2.getCpf().equals(dadosCliente.getCpf())) {
				clientesCpf.add(cliente2);
			}
		}
		// SE DELTA > 1, SIGNIFICA Q O NOVO EMAIL JÁ ESTÁ CADASTRADO
		if (clienteRepository.count() - clientesMail.size() > 1) {
			throw new EmailException("Email já cadastrado");
		}
		if (clienteRepository.count() - clientesUser.size() > 1) {
			throw new UserException("Nome de usuário já cadastrado");
		}
		if (clienteRepository.count() - clientesCpf.size() > 1) {
			throw new CpfException("Cpf já cadastrado");
		}
		dadosCliente.setIdCliente(id);
		String senha = dadosCliente.getSenha();
		dadosCliente.setSenha(bCryptPasswordEncoder.encode(senha));
		clienteRepository.save(dadosCliente);
		ClienteDTO clienteDTO = new ClienteDTO(dadosCliente);
		mailConfig.sendEmailAtualizar(dadosCliente.getEmail(), "Atualização de cadastro",
				clienteDTO.toStringAtualizar());
		return cliente;
	}
}
