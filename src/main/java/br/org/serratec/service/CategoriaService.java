package br.org.serratec.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.serratec.model.Categoria;
import br.org.serratec.repository.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria buscar(Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.get();
	}

}
