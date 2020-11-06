package com.ludmylla.spring.loja.service;

import java.util.List;

import com.ludmylla.spring.loja.model.Categoria;

public interface CategoriaService {

	Long salvar(Categoria categoria);
	
	List<Categoria> findCategoryProduct(List<Categoria> categoria);


}
