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
		
		List<Categoria> list = categoriaService.findCategoryProduct(product);
		product.setCategoria(list);
		validations(product);
		Product productSave = productRepository.save(product);
		
		return productSave.getId();
	}

	private void validations(Product product) {
		validIsProductEmpty(product);
		validIsCategoryExist(product);
		validIsProductNull(product);
		validIsListEmpty(product);
		validProductEqualZero(product);
	}

	private void validIsProductEmpty(Product product) {

		boolean isNameBlank = product.getName().isBlank();
		boolean isCodeBlank = product.getCode().isBlank();

		if (isNameBlank || isCodeBlank) {
			throw new IllegalArgumentException("Existem um ou mais itens em branco.");
		}
	}

	private void validIsCategoryExist(Product product) {
		if (product.getCategoria() instanceof NoSuchElementException) {
			throw new IllegalArgumentException("Categoria não existe, ou está em branco.");
		}
	}

	private void validIsProductNull(Product product) {

		boolean isPriceNull = product.getPrice() == null;
		boolean isQuantityNull = product.getQuantity() == null;
		boolean isCategoryNull = product.getCategoria() == null;

		if (isPriceNull || isQuantityNull || isCategoryNull) {
			throw new IllegalArgumentException(" Existem um ou mais campos em branco.");
		}
	}

	private void validIsListEmpty(Product product) {
		boolean isListCategoriaEmpty = product.getCategoria().isEmpty();
		if (isListCategoriaEmpty) {
			throw new IllegalArgumentException("Categoria deve ser informada!");
		}
	}

	private void validProductEqualZero(Product product) {
		boolean priceEqualZero = product.getPrice().compareTo(BigDecimal.ZERO) <= 0;
		if (priceEqualZero) {
			throw new IllegalArgumentException("Preço: não pode ser zero.");
		}
	}

}
