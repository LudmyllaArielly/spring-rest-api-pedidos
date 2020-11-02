package com.ludmylla.spring.loja.service;

import java.util.List;

import com.ludmylla.spring.loja.model.Category;
import com.ludmylla.spring.loja.model.Product;

public interface CategoryService {

	Long save(Category categoria);

	List<Category> findCategoryProduct(Product product);

}
