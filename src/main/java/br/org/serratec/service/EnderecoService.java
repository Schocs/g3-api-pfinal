package br.org.serratec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.org.serratec.dto.EnderecoDTO;
import br.org.serratec.model.Endereco;
import br.org.serratec.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	public List<EnderecoDTO> listar() {
		List<EnderecoDTO> enderecosDTO = new ArrayList<>();
		for (Endereco endereco : enderecoRepository.findAll()) {
			enderecosDTO.add(new EnderecoDTO(endereco));
		}
		return enderecosDTO;
	}

	public EnderecoDTO buscar(String cep) {
		Optional<Endereco> endereco = Optional.ofNullable(enderecoRepository.findByCep(cep));
		if (endereco.isPresent()) {
			return new EnderecoDTO(endereco.get());
		} else {
			RestTemplate rs = new RestTemplate();
			String uri = "http://viacep.com.br/ws/" + cep + "/json";
			Optional<Endereco> viaCep = Optional.ofNullable(rs.getForObject(uri, Endereco.class));
			if (viaCep.get().getCep() != null) {
				String cepCerto = viaCep.get().getCep().replaceAll("-", "");
				viaCep.get().setCep(cepCerto);
				return inserir(viaCep.get());
			} else {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
			}
		}
	}
	
	public EnderecoDTO buscarCliente(String cep) {
		Optional<Endereco> endereco = Optional.ofNullable(enderecoRepository.findByCep(cep));
		if (endereco.isPresent()) {
			return new EnderecoDTO(endereco.get());
		} else {
			RestTemplate rs = new RestTemplate();
			String uri = "http://viacep.com.br/ws/" + cep + "/json";
			Optional<Endereco> viaCep = Optional.ofNullable(rs.getForObject(uri, Endereco.class));
			if (viaCep.get().getCep() != null) {
				String cepCerto = viaCep.get().getCep().replaceAll("-", "");
				viaCep.get().setCep(cepCerto);
				return new EnderecoDTO(viaCep.get());
			} else {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
			}
		}
	}

	public EnderecoDTO inserir(Endereco endereco) {
		endereco = enderecoRepository.save(endereco);
		return new EnderecoDTO(endereco);
	}

	public EnderecoDTO atualizar(Long id, Endereco endereco) {
		Optional<EnderecoDTO> atualizadoEndereco = Optional.ofNullable(buscar(endereco.getCep()));
		if (atualizadoEndereco.isPresent()) {
			endereco.setIdEndereco(id);
			return inserir(endereco);
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	public boolean deletar(Long id) {
		if (enderecoRepository.existsById(id)) {
			enderecoRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
}
