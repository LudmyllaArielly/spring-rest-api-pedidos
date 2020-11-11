package com.ludmylla.spring.loja.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ludmylla.spring.loja.model.Category;
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
	
	@Transactional
	@Modifying
	@Override
	public Long update(Category category) {
		validations(category);
		validIfCategoryExists(category.getId());
		Category categorySave = categoryRepository.save(category);
		return categorySave.getId();
	}



	@Override
	public List<Category> findCategoryProduct(List<Category> category) {
		
		List<Category> list = new ArrayList<>();		
		for (int i = 0; i < category.size(); i++) {
			List<Category> categories = categoryRepository.findByName(category.get(i).getName());
			list.addAll(categories);
		}
		return list;
	}

	private void validations(Category category) {
		validIfTheCategoryNameIsNull(category);
		validCategoryNameIsBlank(category);
		
	}

	private void validCategoryNameIsBlank(Category category) {		
		boolean isNameBlank = category.getName().isBlank();
		if (isNameBlank) {
			throw new IllegalArgumentException("Name cannot be blank.");
		}
	}
	
	private void validIfTheCategoryNameIsNull(Category category) {
		boolean isNameNull = category.getName() == null;
		if(isNameNull) {
			throw new IllegalArgumentException("Name cannot be null");
		}
	}
	
	private void validIfCategoryExists(Long id) {
		Optional<Category> categories = categoryRepository.findById(id);
		boolean isCategoryExits = categories.isEmpty();
		if(isCategoryExits) {
			throw new IllegalArgumentException("Category id does not exist");
		}
	}

}
