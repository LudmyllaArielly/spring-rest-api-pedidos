package com.ludmylla.spring.loja.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ludmylla.spring.loja.mapper.CategoryMapper;
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
		Category categories = CategoryMapper.INSTANCE.ToCategoria(category);
		categories = categoryRepository.save(categories);
		return categories.getId();
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
		validCategoryNameIsEmpty(category);
	}

	private void validCategoryNameIsEmpty(Category category) {		
		boolean isNameBlank = category.getName().isBlank();
		if (isNameBlank) {
			throw new IllegalArgumentException("Name cannot be blank.");
		}
	}

}
