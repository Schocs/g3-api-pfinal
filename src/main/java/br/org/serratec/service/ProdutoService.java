package br.org.serratec.service;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.dto.ProdutoDTO;
import br.org.serratec.model.Produto;
import br.org.serratec.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FotoProdutoService fotoService;

	public List<ProdutoDTO> listarTodosService() {
		List<ProdutoDTO> dtos = new ArrayList<>();
		for (Produto produtos : produtoRepository.findAll()) {
			dtos.add(inserirUriFoto(produtos));
		}
		return dtos;
	}

	public Optional<Produto> listar(Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);

		return produto;
	}

	public ProdutoDTO cadastrarProduto(Produto produto, MultipartFile file) throws IOException {
		fotoService.inserir(produtoRepository.save(produto), file);
		return inserirUriFoto(produto);
	}

	public Optional<Produto> atualizarService(Long id, Produto dadosProduto) {
		Optional<Produto> produto = produtoRepository.findById(id);

		if (!produto.isPresent()) {
			return produto;
		}
		dadosProduto.setIdproduto(id);
		produtoRepository.save(dadosProduto);

		return produto;
	}

	public boolean deletar(Long id) {

		if (!produtoRepository.existsById(id)) {
			return false;
		}
		produtoRepository.deleteById(id);
		return true;
	}

	private ProdutoDTO inserirUriFoto(Produto produto) {
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/produtos/{id}/foto")
				.buildAndExpand(produto.getIdproduto()).toUri();
		ProdutoDTO dto = new ProdutoDTO(produto);
		dto.setUri(uri.toString());
		return dto;
	}

}