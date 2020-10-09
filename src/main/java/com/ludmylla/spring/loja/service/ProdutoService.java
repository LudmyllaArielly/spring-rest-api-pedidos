package com.ludmylla.spring.loja.service;

import java.util.List;

import com.ludmylla.spring.loja.dto.CategoriaDto;
import com.ludmylla.spring.loja.model.Categoria;
import com.ludmylla.spring.loja.model.Produto;
import com.ludmylla.spring.loja.model.ProdutoCategoriaLista;

public interface ProdutoService {
	
	Long salvar (Produto produto);

	Long salvar (ProdutoCategoriaLista produto);

	List<ProdutoCategoriaLista> listar();
	
	//void atualizar(Long id, String nome);
	
	void deletar(Long id);

	Long atualizar(ProdutoCategoriaLista produtoCategoriaLista);
}
