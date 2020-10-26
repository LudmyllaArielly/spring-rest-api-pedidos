package com.ludmylla.spring.loja.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ludmylla.spring.loja.model.Categoria;
import com.ludmylla.spring.loja.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	@Transactional
	public Long salvar(Categoria categoria) {

		valida(categoria.getNome());
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		return categoriaSalva.getId();
	}

	@Override
	@Transactional
	public List<Categoria> listar() {
		List<Categoria> categorias = new ArrayList<>();
		Iterable<Categoria> list = categoriaRepository.findAll();
		list.forEach(categorias::add);
		return categorias;
	}

	@Override
	@Transactional
	public List<Categoria> findByName(String nome) {
		List<Categoria> categorias = new ArrayList<>();
		Iterable<Categoria> list = categoriaRepository.findByNome(nome);
		list.forEach(categorias::add);

		return categorias;

	}

	private void valida(String nome) {
		if (nome.isEmpty()) {
			throw new IllegalArgumentException("O nome não pode ser vazio");
		}

		// verifcar se a categoria existe
		List<Categoria> categorias = categoriaRepository.findByNomeMatheus(nome);

		boolean isCategoriaExiste = !categorias.isEmpty();
		if (isCategoriaExiste) {
			throw new DataIntegrityViolationException("Nome existente", null);
		}

	}

	@Override
	@Transactional
	public void atualizar(Long id, String nome) {
		// valida(nome);
		Optional<Categoria> categorias = categoriaRepository.findById(id);
		Categoria categoria = categorias.get();
		categoria.setNome(nome);
		categoriaRepository.save(categoria);
	}

	@Override
	@Transactional
	public void deletar(Long id) {
		Optional<Categoria> categorias = categoriaRepository.findById(id);
		Categoria categoria = categorias.get();
		categoriaRepository.delete(categoria);

	}

	@Override
	@Transactional
	public Categoria findByNameCat(String nome) {

		Optional<Categoria> listaCategoria = categoriaRepository.findByNomeCat(nome);
		return listaCategoria.get();
	}
	

	@Override
	@Transactional
	public Categoria findById(Long id) {

		Optional<Categoria> listaCategoria = categoriaRepository.findById(id);
		return listaCategoria.get();
	}


	/*private void validaExiste(String nome, Categoria categoria, Exception DataIntegrityViolationException)
			throws Exception {
		if (nome != null) {
			List<Categoria> nomeExiste = findByName(nome);
			if (categoria.getNome().equals(nomeExiste)) {
				throw new DataIntegrityViolationException("Nome existente", null);
			}
		}

	}*/

	@Override
	public List<Categoria> findByNameAndId(String nome) {
		List<Categoria> categoria = categoriaRepository.findByNameAndId(nome);
		return categoria;
	}

	





	/*
	 * @Override public List<Categoria> findByName2(String nome) { List<Categoria>
	 * categoria = categoriaRepository.findByName2(nome); return categoria; }
	 */

	/*
	 * public void valida(Categoria categoria, String nome, Exception
	 * IllegalArgumentException, Exception DataIntegrityViolationException) throws
	 * Exception { Categoria categoriaCampoNome = (Categoria)
	 * categoriaRepository.findByNome(nome);
	 * 
	 * if(categoria != null) { throw IllegalArgumentException; } else
	 * if(categoria.n) { throw DataIntegrityViolationException; }
	 * 
	 * }
	 */

}
