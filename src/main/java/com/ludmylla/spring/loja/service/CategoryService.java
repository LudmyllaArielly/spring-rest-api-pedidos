package com.ludmylla.spring.loja.service;

import java.util.List;

import com.ludmylla.spring.loja.model.Category;

public interface CategoryService {

	Long save(Category categoria);
	
	Long update(Category category);

	List<Category> list();

	List<Category> findCategoryProduct(List<Category> category);

}
