package com.ludmylla.spring.loja.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ludmylla.spring.loja.model.Categoria;
import com.ludmylla.spring.loja.model.Product;
import com.ludmylla.spring.loja.repository.ProductRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoriaService categoriaService;

	@Transactional
	@Override
	public Long save(Product product) {
		List<Categoria> list = categoriaService.findCategoryProduct(product.getCategoria());
		product.setCategoria(list);
		validations(product);
		Product productSave = productRepository.save(product);
		
		return productSave.getId();
	}

	private void validations(Product product) {
		validIfProductAttributesIsEmpty(product);
		validIfCategoryExist(product);
		validIfProductAttributesAndNull(product);
		validIfProductCategoryListIsEmpty(product);
		validIfProductPriceIsEqualToZero(product);

	}

	private void validIfProductAttributesIsEmpty(Product product) {

		boolean isNameBlank = product.getName().isBlank();
		boolean isCodeBlank = product.getCode().isBlank();

		if (isNameBlank || isCodeBlank) {
			throw new IllegalArgumentException("Existem um ou mais itens em branco.");
		}
	}

	private void validIfCategoryExist(Product product) {
		if (product.getCategoria() instanceof NoSuchElementException) {
			throw new IllegalArgumentException("Categoria não existe, ou está em branco.");
		}
	}


	private void validIfProductAttributesAndNull(Product product) {

		boolean isPriceNull = product.getPrice() == null;
		boolean isQuantityNull = product.getQuantity() == null;
		boolean isCategoryNull = product.getCategoria() == null;

		if (isPriceNull || isQuantityNull || isCategoryNull) {
			throw new IllegalArgumentException(" Existem um ou mais campos nulos.");
		}
	}

	private void validIfProductCategoryListIsEmpty(Product product) {
		boolean isListCategoriaEmpty = product.getCategoria().isEmpty();
		if (isListCategoriaEmpty) {
			throw new IllegalArgumentException("Categoria deve ser informada!");
		}
	}


	private void validIfProductPriceIsEqualToZero(Product product) {
		boolean priceEqualZero = product.getPrice().compareTo(BigDecimal.ZERO) <= 0;
		if (priceEqualZero) {
			throw new IllegalArgumentException("Preço: não pode ser zero.");
		}
	}

}
