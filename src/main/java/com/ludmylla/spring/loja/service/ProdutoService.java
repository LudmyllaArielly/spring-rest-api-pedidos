package com.ludmylla.spring.loja.service;

import java.util.List;

import com.ludmylla.spring.loja.model.Product;

public interface ProdutoService {

	Long save(Product product);

	Long update(Product product);

	void delete(Long id);

	List<Product> list();


}
