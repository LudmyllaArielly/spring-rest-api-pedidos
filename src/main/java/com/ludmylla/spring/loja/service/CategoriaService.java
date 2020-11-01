package com.ludmylla.spring.loja.service;

import java.util.List;

import com.ludmylla.spring.loja.model.Categoria;
import com.ludmylla.spring.loja.model.Product;

public interface CategoriaService {

	Long salvar(Categoria categoria);

	List<Categoria> findCategoryProduct(Product product);

}
