package com.ludmylla.spring.loja.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ludmylla.spring.loja.mapper.ProdutoMapper;
import com.ludmylla.spring.loja.model.Categoria;
import com.ludmylla.spring.loja.model.Product;
import com.ludmylla.spring.loja.repository.CategoriaRepository;
import com.ludmylla.spring.loja.repository.ProductRepository;


@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Transactional
	@Override
	public Long save(Product product) {
		validations(product);
		findCategory(product);
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
	
	@Override
	public void findCategory(Product product) {
		List<Categoria> categoria = product.getCategoria();
		List<Categoria> list = new ArrayList<>();
		for (int i = 0; i < categoria.size(); i++) {
			List<Categoria> categorias = categoriaRepository.findByName(categoria.get(i).getNome());
			list.addAll(categorias);
		}
		isListEmpty(list);
		product.setCategoria(list);
	}
	
	private void isListEmpty(List<Categoria > list) {
		if (list.isEmpty()) {
			throw new IllegalArgumentException("Categoria deve ser informada!");
		}
	}
	

	private void validations(Product product) {
		validIsProductEmpty(product);
		validIsCategoryExist(product);
		validIsProductExist(product);
	}
	
	private void validIsProductEmpty(Product product) {

		boolean isNameBlank = product.getName().isBlank();
		boolean isCodeBlank = product.getCode().isBlank();
		boolean isCategoryBlank = product.getCategoria().isEmpty();

		if (isNameBlank || isCodeBlank || isCategoryBlank ) {
			throw new IllegalArgumentException("Existem um ou mais itens em branco.");
		}

		if (product.getPrice() == null || product.getQuantity() == null 
				 || product.getCategoria() == null){
			throw new IllegalArgumentException(" Existem um ou mais campos em branco.");
		}
	}

	private void validIsCategoryExist(Product product) {
		if (product.getCategoria() instanceof NoSuchElementException) {
			throw new IllegalArgumentException("Categoria não existe, ou está em branco.");
		}
		
	}

	private void validIsProductExist(Product product) {

		List<Product> productName = 
				productRepository.findByName(product.getName());

		List<Product> productCode =
				productRepository.findByCode(product.getCode());

		boolean nameExist = !productName.isEmpty();
		boolean codeExist = !productCode.isEmpty();

		if (nameExist) {
			throw new DataIntegrityViolationException("Nome já existe.");
		}

		if (codeExist) {
			throw new DataIntegrityViolationException("Código já existe.");
		}

	}

	private void validIsProductIdExist(Long id) {
		Optional<Product> product = 
				productRepository.findById(id);

		boolean isIdExist = product.isEmpty();

		if (isIdExist) {
			throw new IllegalArgumentException("ID não existe.");
		}
	}

}
