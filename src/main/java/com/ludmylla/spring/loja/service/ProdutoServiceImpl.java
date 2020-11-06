package com.ludmylla.spring.loja.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ludmylla.spring.loja.mapper.ProdutoMapper;
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

	@Transactional
	@Override
	public Long update(Product product) {
		validIsProductIdExist(product.getId());
		validations(product);
		Product productMapper = ProdutoMapper.INSTANCE.productToproduct(product);
		productMapper = productRepository.save(product);
		return productMapper.getId();

	}

	@Override
	@Transactional
	public List<Product> list() {
		List<Product> products = new ArrayList<>();
		Iterable<Product> list = productRepository.findAll();
		list.forEach(products::add);
		return products;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		validIsProductIdExist(id);
		Optional<Product> products = productRepository.findById(id);
		Product product = products.get();

		productRepository.delete(product);

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

	private void validIsProductIdExist(Long id) {
		Optional<Product> product = productRepository.findById(id);

		boolean isIdExist = product.isEmpty();

		if (isIdExist) {
			throw new IllegalArgumentException("ID não existe.");
		}
	}

}
