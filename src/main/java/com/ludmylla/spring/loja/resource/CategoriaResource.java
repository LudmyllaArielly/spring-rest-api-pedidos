package com.ludmylla.spring.loja.resource;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ludmylla.spring.loja.dto.CategoriaDto2;
import com.ludmylla.spring.loja.mapper.CategoriaMapper;
import com.ludmylla.spring.loja.model.Categoria;
import com.ludmylla.spring.loja.service.CategoriaService;

@RestController
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@PostMapping(path = "/categoria")
	public ResponseEntity<String> cadastrar(@Valid @RequestBody CategoriaDto2 categoriaDto2) {

		try {
			Categoria categoria = CategoriaMapper.INSTANCE.dtoToCategoria(categoriaDto2);

			Long id = categoriaService.salvar(categoria);
			
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Date() + "Categoria adicionada, id: " + id);
			
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Falha ao adicionar: " + ex.getMessage());
		}

	}

}
