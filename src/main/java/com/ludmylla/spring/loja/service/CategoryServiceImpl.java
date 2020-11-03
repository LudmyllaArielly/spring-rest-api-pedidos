package com.ludmylla.spring.loja.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ludmylla.spring.loja.model.Category;
import com.ludmylla.spring.loja.model.Product;
import com.ludmylla.spring.loja.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository ;

	@Override
	@Transactional
	public Long save(Category category) {
		validations(category);
		Category categorySave = categoryRepository.save(category);
		return categorySave.getId();
	}

	@Override
	public List<Category> findCategoryProduct(Product product) {
		List<Category> category = product.getCategories();
		List<Category> list = new ArrayList<>();
		
		for (int i = 0; i < category.size(); i++) {
			List<Category> categories = categoryRepository.findByName(category.get(i).getName());
			list.addAll(categories);
		}
		return list;
	}

	private void validations(Category category) {
		validIsCategoryEmpty(category);
		validIsCategoryNull(category);
	}

	private void validIsCategoryEmpty(Category category) {
		boolean isNomeBlank = category.getName().isBlank();

		if (isNomeBlank) {
			throw new IllegalArgumentException("Name cannot be blank.");
		}
	}

	private void validIsCategoryNull(Category category) {
		boolean isNomeNull = category.getName() == null;

		if (isNomeNull) {
			throw new IllegalArgumentException("Name cannot be null");
		}
	}

}
