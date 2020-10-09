package com.ludmylla.spring.loja.service;

import java.util.List;

import com.ludmylla.spring.loja.model.Categoria;

public interface CategoriaService {

	Long salvar(Categoria categoria);
	
	List<Categoria> listar();
	
	void atualizar(Long id, String nome);
	
	void deletar(Long id);
	
	List<Categoria> findByName(String nome);

	Categoria findById(Long id);
	
	Categoria findByNameCat(String nome);

	

	

}
