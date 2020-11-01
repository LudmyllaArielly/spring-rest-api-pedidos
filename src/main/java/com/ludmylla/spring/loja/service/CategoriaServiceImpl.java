package com.ludmylla.spring.loja.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ludmylla.spring.loja.mapper.CategoriaMapper;
import com.ludmylla.spring.loja.model.Categoria;
import com.ludmylla.spring.loja.model.Product;
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
	public void atualizar(Long id, Categoria categoria) {
		valida(categoria.getNome());
		Categoria categoriaMapper = CategoriaMapper.INSTANCE.categoriaToCategoria(categoria);
		
		Optional<Categoria> categorias = categoriaRepository.findById(id);
		 categoria = categorias.get();
		 
		
		categoriaRepository.save(categoriaMapper);
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
	public List<Categoria> listar() {
		List<Categoria> categorias = new ArrayList<>();
		List<Categoria> list = categoriaRepository.findAll();
		list.forEach(categorias::add);
		return categorias;
	}

	@Override
	@Transactional
	public List<Categoria> findByName(String nome) {
		List<Categoria> categorias = new ArrayList<>();
		List<Categoria> list = categoriaRepository.findByName(nome);
		list.forEach(categorias::add);
		return categorias;

	}
	
	@Override
	public List<Categoria> findCategoryProduct(Product product) {
		List<Categoria> categoria = product.getCategoria();
		List<Categoria> list = new ArrayList<>();
		for (int i = 0; i < categoria.size(); i++) {
			List<Categoria> categorias = categoriaRepository.findByName(categoria.get(i).getNome());
			list.addAll(categorias);
		}
		return list;
	
		//product.setCategoria(list);
	}
	
	@Override
	@Transactional
	public List<Categoria> findByLikeName(String nome) {
		List<Categoria> list = categoriaRepository.findByLikeName(nome);
		return list;
	}


	private void valida(String nome) {
		if (nome.isEmpty()) {
			throw new IllegalArgumentException("O nome não pode ser vazio");
		}

		// verifcar se a categoria existe
		List<Categoria> categorias = categoriaRepository.findByName(nome);

		boolean isCategoriaExiste = !categorias.isEmpty();
		if (isCategoriaExiste) {
			throw new DataIntegrityViolationException("Nome existente", null);
		}

	}

	
}
