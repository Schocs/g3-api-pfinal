package br.org.serratec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.model.Produto;
import br.org.serratec.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Optional<List<Produto>> listarTodosService() {
		Optional<List<Produto>> produto = Optional.ofNullable(produtoRepository.findAll());

		return produto;
	}

	public Optional<Produto> listar(Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);

		return produto;
	}

	public boolean cadastrarProduto(Produto produto) {

		try {
			// categoriaRepository.save(produto.getCategoria());
			produtoRepository.save(produto);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public Optional<Produto> atualizarService(Long id, Produto dadosProduto) {
		Optional<Produto> produto = produtoRepository.findById(id);

		if (!produto.isPresent()) {
			return produto;
		}
		dadosProduto.setId(id);
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

}