package com.ludmylla.spring.loja.resource;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ludmylla.spring.loja.dto.CategoryInsertDto;
import com.ludmylla.spring.loja.dto.CategoryUpdateDto;
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
			Category category = CategoryMapper.INSTANCE.toCategoryInsertDto(categoryInsertDto);

			Long id = categoryService.save(category);

			return ResponseEntity.status(HttpStatus.CREATED).body(new Date() + " Category added, id: " + id);
		
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new Date() + " That name already exists in category! " + ex.getMessage());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" Failed to add: " + e.getMessage());
		}
	}
	
	
	@PutMapping(path = "/categories")
	public ResponseEntity<String> update(@Valid @RequestBody CategoryUpdateDto categoryUpdateDto) {
		try {
			Category category = CategoryMapper.INSTANCE.toCategoryUpdateDto(categoryUpdateDto);
			Long id = categoryService.update(category);

			return ResponseEntity.status(HttpStatus.OK).body(new Date() + " Category updated: " + id);

		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new Date() + " That name already exists in category! " + ex.getMessage());

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Date() + " Failed to update: " + e.getMessage());
		}
	}

}
