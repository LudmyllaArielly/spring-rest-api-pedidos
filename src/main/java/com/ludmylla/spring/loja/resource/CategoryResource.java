package com.ludmylla.spring.loja.resource;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ludmylla.spring.loja.dto.CategoryInsertDto;
import com.ludmylla.spring.loja.mapper.CategoryMapper;
import com.ludmylla.spring.loja.model.Category;
import com.ludmylla.spring.loja.service.CategoryService;

@RestController
public class CategoryResource {

	@Autowired
	private CategoryService categoryService;

	@PostMapping(path = "/categories")
	public ResponseEntity<String> save(@Valid @RequestBody CategoryInsertDto categoryInsertDto) {

		try {
			Category category = CategoryMapper.INSTANCE
					.dtoInsertCategory(categoryInsertDto);

			Long id = categoryService.save(category);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Date() + "Category added, id: " + id);
			
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Failed to add: " + ex.getMessage());
		}

	}

}
