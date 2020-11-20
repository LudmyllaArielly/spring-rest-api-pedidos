package com.ludmylla.spring.loja.resource;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ludmylla.spring.loja.dto.ProductInsertDto;
import com.ludmylla.spring.loja.dto.ProductListDto;
import com.ludmylla.spring.loja.dto.ProductUpdateDto;
import com.ludmylla.spring.loja.mapper.ProductMapper;
import com.ludmylla.spring.loja.model.Product;
import com.ludmylla.spring.loja.service.ProductService;

@RestController
public class ProductResource {

	@Autowired
	private ProductService productService;

	@PostMapping(path = "/products")
	public ResponseEntity<String> save(@Valid @RequestBody ProductInsertDto productInsertDto) {
		try {
			Product product = ProductMapper.INSTANCE.toProductInsertDto(productInsertDto);

			productService.save(product);

			return ResponseEntity.status(HttpStatus.CREATED).body(new Date() + " Product added ");

		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new Date() + " Already exists check: " + ex.getMessage());

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Date() + " Failed to add: " + e.getMessage());
		}
	}

	@GetMapping(path = "/products")
	public ResponseEntity<List<ProductListDto>> listALL() {
		try {
			List<Product> products = productService.listAll();

			List<ProductListDto> list = ProductMapper.INSTANCE.dtoProductListDto(products);
			return new ResponseEntity<List<ProductListDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping(path = "/products")
	public ResponseEntity<String> update(@Valid @RequestBody ProductUpdateDto productUpdateDto) {
		try {
			Product product = ProductMapper.INSTANCE.toProductUpdateDto(productUpdateDto);

			productService.update(product);
			return ResponseEntity.status(HttpStatus.OK).body(new Date() + " Product updated ");
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new Date() + " Already exists check: " + ex.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Date() + " Failed to update: " + e.getMessage());
		}
	}

	@DeleteMapping(path = "/products")
	public ResponseEntity<String> delete(Long id) {
		try {
			productService.delete(id);

			return ResponseEntity.status(HttpStatus.OK).body(new Date() + " Successfully deleted: ");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new Date() + " Failed to delete: " + e.getMessage());
		}
	}

}
