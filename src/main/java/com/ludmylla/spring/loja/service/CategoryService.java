package com.ludmylla.spring.loja.service;

import java.util.List;

import com.ludmylla.spring.loja.model.Category;

public interface CategoryService {

	Long save(Category category);
	
	void delete(Long id);

	List<Category> findCategoryProduct(List<Category> category);

}
