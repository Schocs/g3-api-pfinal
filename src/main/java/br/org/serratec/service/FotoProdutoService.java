package br.org.serratec.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.org.serratec.model.FotoProduto;
import br.org.serratec.model.Produto;
import br.org.serratec.repository.FotoProdutoRepository;

@Service
public class FotoProdutoService {

	@Autowired
	private FotoProdutoRepository fotoRepository;
	
	public FotoProduto inserir(Produto produto, MultipartFile file) throws IOException{
		FotoProduto foto = new FotoProduto();
		foto.setNome(file.getName());
		foto.setTipo(file.getContentType());
		foto.setDados(file.getBytes());
		foto.setProduto(produto);
		return fotoRepository.save(foto);
	}
	
	public FotoProduto buscar(Long id) {
		Optional<FotoProduto> foto = fotoRepository.findById(id);
		if(foto.isPresent()) {
			return foto.get();
		} else {
			return null;
		}
	}
}
