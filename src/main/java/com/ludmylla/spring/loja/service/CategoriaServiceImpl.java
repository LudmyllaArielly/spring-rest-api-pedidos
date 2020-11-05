package com.ludmylla.spring.loja.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ludmylla.spring.loja.mapper.CategoriaMapper;
import com.ludmylla.spring.loja.model.Categoria;
import com.ludmylla.spring.loja.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	@Transactional
	public Long salvar(Categoria categoria) {
		validations(categoria);
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		return categoriaSalva.getId();
	}


	@Transactional
	public void atualizar(Long id, Categoria categoria) {
		
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
	public List<Categoria> findCategoryProduct(List<Categoria> categoria) {
		
		List<Categoria> list = new ArrayList<>();
		
		for (int i = 0; i < categoria.size(); i++) {
			List<Categoria> categorias = categoriaRepository
					.findByName(categoria.get(i).getNome());
			list.addAll(categorias);
		}
		return list;
	}
	
	@Override
	@Transactional
	public List<Categoria> findByLikeName(String nome) {
		List<Categoria> list = categoriaRepository.findByLikeName(nome);
		return list;

	}

	private void validations(Categoria categoria) {
		validIfCategoryAttributeIsEmpty(categoria);
		
	}



	private void validIfCategoryAttributeIsEmpty(Categoria categoria) {	

		boolean isNomeBlank = categoria.getNome().isBlank();

		if (isNomeBlank) {
			throw new IllegalArgumentException("Nome não pode estar em branco.");
		}
	}

	


}
