package com.ludmylla.spring.loja.service;

import java.util.List;

import com.ludmylla.spring.loja.model.Categoria;
import com.ludmylla.spring.loja.model.Product;

public interface CategoriaService {

	Long salvar(Categoria categoria);

	List<Categoria> listar();

	void atualizar(Long id, Categoria categoria);

	void deletar(Long id);

	List<Categoria> findByName(String nome);

	List<Categoria> findByLikeName(String nome);
	
	List<Categoria> findCategoryProduct(Product product);


}
