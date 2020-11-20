package com.ludmylla.spring.loja.service;

import java.util.List;

import com.ludmylla.spring.loja.model.Product;

public interface ProductService {

	Long save(Product product);

	List<Product> listAll();
	
	void update (Product product);
	
	void delete (Long id);

}
