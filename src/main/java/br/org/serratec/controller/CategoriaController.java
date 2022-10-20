package br.org.serratec.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.org.serratec.model.Categoria;
import br.org.serratec.repository.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	@Autowired
	private CategoriaRepository categoriaRepository;

	@PostMapping("/categoria)")
	@ResponseStatus(HttpStatus.CREATED)
	public Categoria inserir(@Valid @RequestBody Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@PostMapping("/add")
	public String novo(@Valid Categoria categoria, BindingResult result) {

		if (result.hasFieldErrors()) {
			return "redirect:/form";
		}

		categoriaRepository.save(categoria);

		return "redirect:/home";
	}

	@GetMapping("/produto/{id}")
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Categoria> findById(@PathVariable long id) {
		return categoriaRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable long id) {
		return categoriaRepository.findById(id).map(record -> {
			categoriaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Categoria categoria) {
		return categoriaRepository.findById(id).map(record -> {
			record.setNome(categoria.getNome());
			record.setDescricao(categoria.getDescricao());
			Categoria updated = categoriaRepository.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

}
