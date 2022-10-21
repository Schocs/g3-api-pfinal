package br.org.serratec.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.model.Categoria;
import br.org.serratec.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria salvarCategoria(Categoria categoria) {
		return repository.save(categoria);
	}

	public Optional<Categoria> encontrarCategoria(Long id) {
		return repository.findById(id);
	}

	public void deletarCategoria(Long id) {
		Optional<Categoria> categoriaExistente = repository.findById(id);
		if (categoriaExistente.isPresent())
			repository.deleteById(id);
	}

	public List<Categoria> retornaTodasCategorias() {
		List<Categoria> todasCategorias = repository.findAll();
		return !todasCategorias.isEmpty() ? todasCategorias : Collections.emptyList();
	}

}
