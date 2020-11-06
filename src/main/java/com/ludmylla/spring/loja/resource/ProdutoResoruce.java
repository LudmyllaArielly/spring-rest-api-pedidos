package com.ludmylla.spring.loja.resource;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ludmylla.spring.loja.dto.ProdutoDto2;
import com.ludmylla.spring.loja.mapper.ProdutoMapper;
import com.ludmylla.spring.loja.model.Product;
import com.ludmylla.spring.loja.service.ProdutoService;

@RestController
public class ProdutoResoruce {

	@Autowired
	private ProdutoService produtoService;

	@PostMapping(path = "/produtos")
	public ResponseEntity<String> salvar(@Valid @RequestBody ProdutoDto2 produtoDto2) {
		try {
			Product product = ProdutoMapper.INSTANCE.dtoToProduto(produtoDto2);

			Long id = produtoService.save(product);

			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Date() + " Produto adicionado, id: " + id);

		}  catch (DataIntegrityViolationException ex) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new Date() + "Nome ou código existente" + ex.getMessage());		
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Date() + " Falha ao adicionar: " + e.getMessage());
		}
	}

}
