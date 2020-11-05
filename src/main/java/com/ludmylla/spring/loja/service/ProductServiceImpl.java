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
		validIfProductAttributesIsEmpty(product);
		validIfCategoryExist(product);
		validIfProductAttributesAndNull(product);
		validIfProductListCategoryEmpty(product);
		validIfProductPriceIsEqualToZero(product);
	}

	private void validIfProductAttributesIsEmpty(Product product) {

		boolean isNameBlank = product.getName().isBlank();
		boolean isCodeBlank = product.getCode().isBlank();

		if (isNameBlank || isCodeBlank) {
			throw new IllegalArgumentException("There are one or more blank items.");
		}
	}

	private void validIfCategoryExist(Product product) {
		if (product.getCategories() instanceof NoSuchElementException) {
			throw new IllegalArgumentException("Categoria não existe, ou está em branco.");
		}
	}

	private void validIfProductAttributesAndNull(Product product) {

		boolean isPriceNull = product.getPrice() == null;
		boolean isQuantityNull = product.getQuantity() == null;
		boolean isCategoryNull = product.getCategories() == null;

		if (isPriceNull || isQuantityNull || isCategoryNull) {
			throw new IllegalArgumentException("There are one or more blank items.");
		}
	}

	private void validIfProductListCategoryEmpty(Product product) {
		boolean isListCategoryEmpty = product.getCategories().isEmpty();
		if (isListCategoryEmpty) {
			throw new IllegalArgumentException("Category must be informed!");
		}
	}

	private void validIfProductPriceIsEqualToZero(Product product) {
		boolean priceEqualZero = product.getPrice().compareTo(BigDecimal.ZERO) <= 0;
		if (priceEqualZero) {
			throw new IllegalArgumentException("Preço: não pode ser zero.");
		}
	}

}
