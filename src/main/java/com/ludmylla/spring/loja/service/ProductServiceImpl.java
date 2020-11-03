package com.ludmylla.spring.loja.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ludmylla.spring.loja.model.Category;
import com.ludmylla.spring.loja.model.Product;
import com.ludmylla.spring.loja.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryService categoryService;

	@Transactional
	@Override
	public Long save(Product product) {
		
		List<Category> list = categoryService.findCategoryProduct(product.getCategories());
		product.setCategories(list);
		validations(product);
		Product productSave = productRepository.save(product);
		
		return productSave.getId();
	}

	private void validations(Product product) {		
		validIfAttributesInProductAndEmpty(product);
		validIsCategoryExist(product);
		validIfAttributesInProductAndNull(product);
		validIfTheCategoryListOfProductIsEmpty(product);
		validIfPriceOfProductIsEqualZero(product);
	}

	private void validIfAttributesInProductAndEmpty(Product product) {

		boolean isNameBlank = product.getName().isBlank();
		boolean isCodeBlank = product.getCode().isBlank();

		if (isNameBlank || isCodeBlank) {
			throw new IllegalArgumentException("There are one or more blank items.");
		}
	}

	private void validIsCategoryExist(Product product) {
		if (product.getCategories() instanceof NoSuchElementException) {
			throw new IllegalArgumentException("Category does not exist, or is blank.");
		}
	}

	private void validIfAttributesInProductAndNull(Product product) {

		boolean isPriceNull = product.getPrice() == null;
		boolean isQuantityNull = product.getQuantity() == null;
		boolean isCategoryNull = product.getCategories() == null;

		if (isPriceNull || isQuantityNull || isCategoryNull) {
			throw new IllegalArgumentException("There are one or more blank items.");
		}
	}

	private void validIfTheCategoryListOfProductIsEmpty(Product product) {
		boolean isListCategoryEmpty = product.getCategories().isEmpty();
		if (isListCategoryEmpty) {
			throw new IllegalArgumentException("Category must be informed!");
		}
	}

	private void validIfPriceOfProductIsEqualZero(Product product) {
		boolean isPriceEqualZero = product.getPrice().compareTo(BigDecimal.ZERO) <= 0;
		if (isPriceEqualZero) {
			throw new IllegalArgumentException("Price: cannot be zero.");
		}			
	}

}
