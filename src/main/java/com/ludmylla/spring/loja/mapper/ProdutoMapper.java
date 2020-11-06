package com.ludmylla.spring.loja.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ludmylla.spring.loja.dto.ProdutoDto2;
import com.ludmylla.spring.loja.model.Product;

@Mapper(uses = { CategoriaMapper.class})
public interface ProdutoMapper {
	
	ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);
	
	Product productToproduct (Product source);
	
	@Mapping(target = "categoria", source = "listCategoriaDto2")
	@Mapping(target = "id", ignore = true)
	Product dtoToProduto (ProdutoDto2 source);
	
	
}