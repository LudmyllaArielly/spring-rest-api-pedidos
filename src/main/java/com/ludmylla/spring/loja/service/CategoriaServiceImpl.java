package com.ludmylla.spring.loja.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

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

		valida(categoria.getNome(), categoria);
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
	
	private void valida (String nome, Categoria categoria) {
		if (nome.isEmpty()) {
			throw new IllegalArgumentException("O nome não pode ser vazio");
		}
		
		List<Categoria> categorias = new ArrayList<>();
		Iterable<Categoria> nomeExiste = categoriaRepository.findByNome(nome);
		nomeExiste.forEach(categorias::add);
		
		
		if(categorias instanceof ConstraintViolationException){
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

	private void validaExiste(String nome, Categoria categoria, Exception DataIntegrityViolationException)
			throws Exception {
		if (nome != null) {
			List<Categoria> nomeExiste = findByName(nome);
			if (categoria.getNome().equals(nomeExiste)) {
				throw new DataIntegrityViolationException("Nome existente", null);
			}
		}

	}

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
