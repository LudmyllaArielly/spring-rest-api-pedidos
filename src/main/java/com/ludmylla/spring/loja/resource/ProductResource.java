package com.ludmylla.spring.loja.resource;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ludmylla.spring.loja.dto.ProductInsertDto;
import com.ludmylla.spring.loja.mapper.ProductMapper;
import com.ludmylla.spring.loja.model.Product;
import com.ludmylla.spring.loja.service.ProductService;

@RestController
public class ProductResource {

	@Autowired
	private ProductService produtoService;

	@PostMapping(path = "/products")
	public ResponseEntity<String> save(@Valid @RequestBody ProductInsertDto productInsertDto) {
		try {
			Product product = ProductMapper.INSTANCE
					.dtoInsertProduct(productInsertDto);
					
			Long id = produtoService.save(product);

			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Date() + " Product added, id: " + id);

		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityViolationException("Existing code or name." + ex.getMessage());
		
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Date() + " Failed to add: " + e.getMessage());
		}
	}

}
