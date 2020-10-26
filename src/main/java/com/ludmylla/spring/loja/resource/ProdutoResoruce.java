package com.ludmylla.spring.loja.resource;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ludmylla.spring.loja.dto.CategoriaDto2;
import com.ludmylla.spring.loja.dto.ProdutoDto;
import com.ludmylla.spring.loja.dto.ProdutoDto2;
import com.ludmylla.spring.loja.dto.ProdutoGetDto;
import com.ludmylla.spring.loja.dto.ProdutoPutDto;
import com.ludmylla.spring.loja.mapper.ProdutoMapper;
import com.ludmylla.spring.loja.model.Categoria;
import com.ludmylla.spring.loja.model.Produto;
import com.ludmylla.spring.loja.model.ProdutoCategoriaLista;
import com.ludmylla.spring.loja.service.CategoriaService;
import com.ludmylla.spring.loja.service.ProdutoService;

@RestController
public class ProdutoResoruce {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private CategoriaService categoriaService;

	@PostMapping(path = "/produto")
	public ResponseEntity<ProdutoDto> salvar(@RequestBody ProdutoDto produtoDto) {
		// converter o DTO para Entity
		Produto produto = new Produto();
		produto.setNome(produtoDto.getNome());
		// Entity
		// busca categoria
		Categoria categoria = categoriaService.findById(produtoDto.getCategoriaDto().getId());
		produto.setCategoria(categoria);
		// salvar
		produtoService.salvar(produto);

		return ResponseEntity.ok(produtoDto);
	}

	@PostMapping(path = "/produto2")
	public ResponseEntity<String> salvar2(@RequestBody ProdutoDto2 produtoDto2, @RequestParam String nome) {
		try {
			ProdutoCategoriaLista produtoCategoriaLista = ProdutoMapper.INSTANCE.dtoToProduto(produtoDto2);
			produtoCategoriaLista.setDataCadastro(OffsetDateTime.now());
			List<Categoria> categorias = categoriaService.findByNameAndId(nome);

			produtoCategoriaLista.setCategoria(categorias);

			Long id = produtoService.salvar(produtoCategoriaLista);

			return ResponseEntity.status(HttpStatus.CREATED).body(new Date() + " Produto adicionado, id: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new Date() + " Falha ao adicionar: " + e.getMessage());
		}
	}

	@GetMapping(path = "/produto2")
	public ResponseEntity<List<ProdutoGetDto>> listarProdutos() {
		try {
			List<ProdutoCategoriaLista> produtos = produtoService.listar();

			List<ProdutoGetDto> list = ProdutoMapper.INSTANCE.dtoGetToProduto(produtos);

			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}

	@PutMapping(path = "/produto2")
	public ResponseEntity<String> atualizar(@RequestBody ProdutoPutDto produtoPutDto) {
		try {
			ProdutoCategoriaLista produtoCategoriaLista = ProdutoMapper.INSTANCE.dtoPutToProduto(produtoPutDto);
			produtoCategoriaLista.getDataCadastro();
			List<Categoria> listaCategorias = new ArrayList<>();
			for (CategoriaDto2 itens : produtoPutDto.getListCategoriaDto2()) {
				Categoria categoria = categoriaService.findByNameCat(itens.getNome());
				listaCategorias.add(categoria);
			}

			produtoCategoriaLista.setCategoria(listaCategorias);

			Long id = produtoService.atualizar(produtoCategoriaLista, produtoPutDto.getId());
			
			return ResponseEntity.status(HttpStatus.OK).body(new Date() +
					"Atualizado com sucesso, id: " + id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Date() + 
					" Falha ao atualizar: " + e.getMessage());
		}
	}

	@DeleteMapping(path = "/produto2")
	public ResponseEntity<String> delete(Long id) {
		try {
			produtoService.deletar(id);

			return ResponseEntity.status(HttpStatus.OK).body(new Date() + " Deletado com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new Date() + " Falha ao deletar: " + e.getMessage());
		}
	}
	
	public List<Categoria> CategoriaNome( Categoria categoria) {
		List<Categoria> categorias = categoriaService.findByNameAndId(categoria.getNome());
		return categorias;
	}

}
