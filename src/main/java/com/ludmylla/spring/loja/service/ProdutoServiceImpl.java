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
import com.ludmylla.spring.loja.model.Produto;
import com.ludmylla.spring.loja.model.ProdutoCategoriaLista;
import com.ludmylla.spring.loja.repository.ProdutoCaltegoriaListaRepository;
import com.ludmylla.spring.loja.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ProdutoCaltegoriaListaRepository produtoCaltegoriaListaRepository;

	@Override
	@Transactional
	public Long salvar(Produto produto) {

		Produto produtoSalvo = produtoRepository.save(produto);
		return produtoSalvo.getId();
	}

	@Override
	public Long salvar(ProdutoCategoriaLista produto) {
		valida(produto);
		validaCategoria(produto);
		validaExiste(produto);
		ProdutoCategoriaLista produtoSalvo = produtoCaltegoriaListaRepository.save(produto);
		return produtoSalvo.getId();
	}
	
	@Transactional
	@Override
	public Long atualizar(ProdutoCategoriaLista produto, Long id) {
		validaIdExists(produto);
		valida(produto);
		validaCategoria(produto);
		validaIdExiste(id);		
		Optional<ProdutoCategoriaLista> produtos = produtoCaltegoriaListaRepository.findById(id);
		ProdutoCategoriaLista produtoMapper = ProdutoMapper.INSTANCE.produtoToproduto(produto);
		ProdutoCategoriaLista pessoaUpdate = produtoCaltegoriaListaRepository.save(produto);
		return pessoaUpdate.getId();

	}

	@Override
	@Transactional
	public List<ProdutoCategoriaLista> listar() {
		List<ProdutoCategoriaLista> produtos = new ArrayList<>();
		Iterable<ProdutoCategoriaLista> list = produtoCaltegoriaListaRepository.findAll();
		list.forEach(produtos::add);
		return produtos;
	}

	@Override
	public void deletar(Long id) {
		validaIdExiste(id);
		Optional<ProdutoCategoriaLista> produtos = produtoCaltegoriaListaRepository.findById(id);
		ProdutoCategoriaLista produto = produtos.get();
		produtoCaltegoriaListaRepository.delete(produto);
	}
	
	private void valida(ProdutoCategoriaLista produto) {
		
		if(produto.getNome().isEmpty() || produto.getNome().isBlank()
				||produto.getUnidade().isEmpty() || produto.getUnidade().isBlank()
				|| produto.getCodigo().isEmpty() || produto.getCodigo().isBlank()
				|| produto.getPeso().isBlank() || produto.getPeso().isBlank()
				|| produto.getCategoria().isEmpty()) {
			
			throw new IllegalArgumentException("Existem um ou mais itens em branco.");			
		}
		
		if(produto.getPreco() == null || produto.getQuantidade() == null) {
			throw  new IllegalArgumentException(" Existem um ou mais campos em branco.");
		}
	}
	
	private void validaCategoria(ProdutoCategoriaLista produto) {
		if(produto.getCategoria() instanceof NoSuchElementException) {
			throw new IllegalArgumentException("Categoria não existe, ou está em branco.");
		}
	}
	
	private void validaExiste(ProdutoCategoriaLista produto) {
		List<ProdutoCategoriaLista> produtoNome = 
				produtoCaltegoriaListaRepository.findByName(produto.getNome());
		
		List<ProdutoCategoriaLista> produtoCodigo =
				produtoCaltegoriaListaRepository.findByCodigo(produto.getCodigo());
		
		boolean nomeExiste = !produtoNome.isEmpty();
		boolean codigoExiste = !produtoCodigo.isEmpty();
		
		if(nomeExiste) {
			throw new DataIntegrityViolationException("Nome já existe.");
		}
		
		if(codigoExiste) {
			throw new DataIntegrityViolationException("Código já existe.");
		}
	}
	
	private void validaIdExiste(Long id) {
		Optional<ProdutoCategoriaLista> produto = produtoCaltegoriaListaRepository.findById(id);
		
		boolean idExiste = produto.isEmpty();
		
		if(idExiste) {
			throw new IllegalArgumentException("ID não existe.");
		}
	}
	
	private void validaIdExists(ProdutoCategoriaLista produto) {
		Optional<ProdutoCategoriaLista> produtos =
				produtoCaltegoriaListaRepository.findById(produto.getId());
		
		boolean idExiste = produtos.isEmpty();
		
		if(idExiste) {
			throw new IllegalArgumentException("ID não existe.");
		}
	}

}
