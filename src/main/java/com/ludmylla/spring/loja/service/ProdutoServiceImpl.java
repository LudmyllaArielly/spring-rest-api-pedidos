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
import com.ludmylla.spring.loja.model.ProdutoCategoriaLista;
import com.ludmylla.spring.loja.repository.ProdutoCaltegoriaListaRepository;


@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoCaltegoriaListaRepository produtoCaltegoriaListaRepository;

	@Transactional
	@Override
	public Long salvar(ProdutoCategoriaLista produto) {
		validacoes(produto);
		ProdutoCategoriaLista produtoSalvo = produtoCaltegoriaListaRepository.save(produto);
		return produtoSalvo.getId();
	}

	@Transactional
	@Override
	public Long atualizar(ProdutoCategoriaLista produto) {
		validacoesUpdate(produto);
		ProdutoCategoriaLista produtoMapper = ProdutoMapper.INSTANCE.produtoToproduto(produto);
		produtoMapper = produtoCaltegoriaListaRepository.save(produto);
		return produtoMapper.getId();

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
	@Transactional
	public void deletar(Long id) {
		validaIdExiste(id);
		Optional<ProdutoCategoriaLista> produtos = produtoCaltegoriaListaRepository.findById(id);
		ProdutoCategoriaLista produto = produtos.get();

		produtoCaltegoriaListaRepository.delete(produto);

	}

	private void validacoes(ProdutoCategoriaLista produto) {
		validaProdutoVazio(produto);
		validaCategoria(produto);
		validaExisteProduto(produto);
	}
	
	private void validacoesUpdate(ProdutoCategoriaLista produto) {
		validaProdutoVazio(produto);
		validaCategoria(produto);
		validaExisteProdutoUpdate(produto);
		validaIdExiste(produto.getId());
	}

	private void validaProdutoVazio(ProdutoCategoriaLista produto) {

		boolean nomeIsBlank = produto.getNome().isBlank();
		boolean codigoIsBlank = produto.getCodigo().isBlank();
		boolean categoriaIsEmpty = produto.getCategoria().isEmpty();

		if (nomeIsBlank || codigoIsBlank || categoriaIsEmpty) {
			throw new IllegalArgumentException("Existem um ou mais itens em branco.");
		}

		if (produto.getPreco() == null || produto.getQuantidade() == null) {
			throw new IllegalArgumentException(" Existem um ou mais campos em branco.");
		}
	}

	private void validaCategoria(ProdutoCategoriaLista produto) {
		if (produto.getCategoria() instanceof NoSuchElementException) {
			throw new IllegalArgumentException("Categoria não existe, ou está em branco.");
		}
	}

	private void validaExisteProduto(ProdutoCategoriaLista produto) {
		List<ProdutoCategoriaLista> produtoNome = produtoCaltegoriaListaRepository.findByName(produto.getNome());

		List<ProdutoCategoriaLista> produtoCodigo = produtoCaltegoriaListaRepository.findByCodigo(produto.getCodigo());

		boolean nomeExiste = !produtoNome.isEmpty();
		boolean codigoExiste = !produtoCodigo.isEmpty();

		if (nomeExiste) {
			throw new DataIntegrityViolationException("Nome já existe.");
		}

		if (codigoExiste) {
			throw new DataIntegrityViolationException("Código já existe.");
		}
	}
	
	private void validaExisteProdutoUpdate(ProdutoCategoriaLista produto) {
		
		List<ProdutoCategoriaLista> produtoNomeEId = 
				produtoCaltegoriaListaRepository
				.findByNameById(produto.getNome(), produto.getId());
		
		List<ProdutoCategoriaLista> produtoCodigoId =
				produtoCaltegoriaListaRepository
				.findByCodigoById(produto.getCodigo(), produto.getId());
		
		boolean nomeAndIdMudou= produtoNomeEId.isEmpty();
		boolean codigoAndIdMudou = produtoCodigoId.isEmpty();
		
		List<ProdutoCategoriaLista> produtoNome = 
				produtoCaltegoriaListaRepository.findByName(produto.getNome());
		
		List<ProdutoCategoriaLista> produtoCodigo = 
				produtoCaltegoriaListaRepository.findByCodigo(produto.getCodigo());

		boolean nomeExiste = !produtoNome.isEmpty();
		boolean codigoExiste = !produtoCodigo.isEmpty();
		
		if(nomeAndIdMudou == nomeExiste) {
			throw new DataIntegrityViolationException("Nome já existe.");
		}
		
		if(codigoAndIdMudou == codigoExiste ) {
			throw new DataIntegrityViolationException("Código já existe.");
		}
		
		/* Se no update eu mudar por exemplo: apenas o preço e deixar o mesmo
		 * nome e codígo, então vai verificar que aquele nome e código que já existe e
		 * pertence ao id, que está sendo atualizado.
		 * Se o nome ou o código for alterado, então essa verificação
		 * nomeAndIdMudou será true, então não vai cadastrar.
		 * Para resolver quando o nome ou código for alterado
		 * fará a verificação se o nome e codigo já existem
		 * no banco, se existe lanca exceção, se não cadastra*/
		
	}

	private void validaIdExiste(Long id) {
		Optional<ProdutoCategoriaLista> produto = produtoCaltegoriaListaRepository.findById(id);

		boolean idExiste = produto.isEmpty();

		if (idExiste) {
			throw new IllegalArgumentException("ID não existe.");
		}
	}

}
