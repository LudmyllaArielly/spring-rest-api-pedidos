package com.ludmylla.spring.loja.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ludmylla.spring.loja.dto.ProdutoDto2;
import com.ludmylla.spring.loja.dto.ProdutoGetDto;
import com.ludmylla.spring.loja.dto.ProdutoPutDto;
import com.ludmylla.spring.loja.model.ProdutoCategoriaLista;

@Mapper(uses = { CategoriaMapper.class})
public interface ProdutoMapper {
	
	ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);
	
	ProdutoCategoriaLista produtoToproduto (ProdutoCategoriaLista source);
	
	@Mapping(target = "categoria", source = "listCategoriaDto2")
	@Mapping(target = "id", ignore = true)
	ProdutoCategoriaLista dtoToProduto (ProdutoDto2 source);
	
	@Mapping(target = "categoria", source = "listCategoriaDto2")
	ProdutoCategoriaLista dtoPutToProduto(ProdutoPutDto source);
	
	@Mapping(target = "categoria", ignore = true)
	@Mapping(target = "id", ignore = true)
	ProdutoCategoriaLista dtoGetToProduto (ProdutoGetDto source);

	List<ProdutoGetDto> dtoGetToProduto(List<ProdutoCategoriaLista> produtos);
}
