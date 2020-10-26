package com.ludmylla.spring.loja.service;

import java.util.List;

import com.ludmylla.spring.loja.model.Produto;
import com.ludmylla.spring.loja.model.ProdutoCategoriaLista;

public interface ProdutoService {

	Long salvar(Produto produto);

	Long salvar(ProdutoCategoriaLista produto);

	Long atualizar(ProdutoCategoriaLista produtoCategoriaLista, Long id);

	void deletar(Long id);

	List<ProdutoCategoriaLista> listar();

}
