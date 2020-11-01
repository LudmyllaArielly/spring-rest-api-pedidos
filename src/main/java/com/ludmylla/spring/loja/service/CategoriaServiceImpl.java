package com.ludmylla.spring.loja.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		validations(categoria);
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		return categoriaSalva.getId();
	}

	@Override
	public List<Categoria> findCategoryProduct(Product product) {
		List<Categoria> categoria = product.getCategoria();
		List<Categoria> list = new ArrayList<>();
		
		for (int i = 0; i < categoria.size(); i++) {
			List<Categoria> categorias = categoriaRepository
					.findByName(categoria.get(i).getNome());
			list.addAll(categorias);
		}
		return list;
	}

	private void validations(Categoria categoria) {
		validIsCategoryEmpty(categoria);
		validIsCategoryNull(categoria);
	}

	private void validIsCategoryEmpty(Categoria categoria) {
		boolean isNomeBlank = categoria.getNome().isBlank();

		if (isNomeBlank) {
			throw new IllegalArgumentException("Nome não pode estar em branco.");
		}
	}

	private void validIsCategoryNull(Categoria categoria) {
		boolean isNomeNull = categoria.getNome() == null;

		if (isNomeNull) {
			throw new IllegalArgumentException("Nome não pode ser nulo.");
		}
	}

}
