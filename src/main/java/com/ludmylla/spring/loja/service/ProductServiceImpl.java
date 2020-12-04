package com.ludmylla.spring.loja.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
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
	
	@Override
	@Transactional
	public List<Product> listAll(){
		List<Product> products = new ArrayList<>();
		Iterable<Product> list = productRepository.findAll();
		list.forEach(products::add);		
		return (List<Product>) list;
	}
	
	
	@Modifying
	@Transactional
	@Override
	public void update(Product product) {
		validIfProductExits(product.getId());
		validations(product);
		List<Category> categories = categoryService.findCategoryProduct(product.getCategories());
		product.setCategories(categories);
		Optional<Product> products = productRepository.findById(product.getId());
		product = products.get();		
		productRepository.save(product);
	
	}
	
	@Transactional
	@Override
	public void delete(Long id) {
		validIfProductExits(id);
		Optional<Product> product = productRepository.findById(id);
		Product products = product.get();
		productRepository.delete(products);
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
			throw new IllegalArgumentException("Category does not exist, or is blank.");
		}
	}

	private void validIfProductAttributesAndNull(Product product) {

		boolean isPriceNull = product.getPrice() == null;
		boolean isQuantityNull = product.getQuantity() == null;
		boolean isCategoryNull = product.getCategories() == null;

		if (isPriceNull || isQuantityNull || isCategoryNull) {
			throw new IllegalArgumentException("There are one or more null items.");
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
			throw new IllegalArgumentException("Price: cannot be zero!");
		}
	}
	
	private void validIfProductExits(Long id) {
		Optional<Product> product = productRepository.findById(id);
		boolean isProductExists = product.isEmpty();
		
		if(isProductExists) {
			throw new NoSuchElementException("This product does not exist!");
		}
	}

}
