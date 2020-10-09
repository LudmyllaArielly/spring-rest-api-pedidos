package com.ludmylla.spring.loja.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ludmylla.spring.loja.model.ProdutoCategoriaLista;
import com.ludmylla.spring.loja.repository.ProdutoCaltegoriaListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ludmylla.spring.loja.dto.CategoriaDto;
import com.ludmylla.spring.loja.model.Categoria;
import com.ludmylla.spring.loja.model.Produto;
import com.ludmylla.spring.loja.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ProdutoCaltegoriaListaRepository produtoCaltegoriaListaRepository;
	
	@Override
	@Transactional
	public Long salvar(Produto produto){

		Produto produtoSalvo = produtoRepository.save(produto);	
		return produtoSalvo.getId();
	}

	@Override
	public Long salvar(ProdutoCategoriaLista produto) {
		ProdutoCategoriaLista produtoSalvo = produtoCaltegoriaListaRepository.save(produto);
		return produtoSalvo.getId();
	}

	@Override
	@Transactional
	public List<ProdutoCategoriaLista> listar() {
		List<ProdutoCategoriaLista> produtos = new ArrayList<>();
		Iterable<ProdutoCategoriaLista> list = produtoCaltegoriaListaRepository.findAll();
		list.forEach(produtos:: add);
		return produtos;
	}


	/*public void atualizar(Long id, String nome, ProdutoCategoriaLista produtoCategoriaLista ) {
		

	}*/

	@Override
	public void deletar(Long id) {
		
		Optional<ProdutoCategoriaLista> produtos = produtoCaltegoriaListaRepository.findById(id);
		ProdutoCategoriaLista produto = produtos.get();
		produtoCaltegoriaListaRepository.delete(produto);
	}

	@Override
	public Long atualizar( ProdutoCategoriaLista produto) {
		ProdutoCategoriaLista produtoSalvo = produtoCaltegoriaListaRepository.save(produto);
		return produtoSalvo.getId();
		
	}

}
