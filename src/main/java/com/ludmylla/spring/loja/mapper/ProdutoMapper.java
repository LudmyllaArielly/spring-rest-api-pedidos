package com.ludmylla.spring.loja.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ludmylla.spring.loja.dto.ProdutoDto2;
import com.ludmylla.spring.loja.dto.ProdutoGetDto;
import com.ludmylla.spring.loja.dto.ProdutoPutDto;
import com.ludmylla.spring.loja.model.Product;

@Mapper(uses = { CategoriaMapper.class})
public interface ProdutoMapper {
	
	ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);
	
	Product productToproduct (Product source);
	
	@Mapping(target = "categoria", source = "listCategoriaDto2")
	@Mapping(target = "id", ignore = true)
	Product dtoToProduto (ProdutoDto2 source);
	
	@Mapping(target = "categoria", source = "listCategoriaDto2")
	Product dtoPutToProduto(ProdutoPutDto source);
	
	@Mapping(target = "categoria", ignore = true)
	@Mapping(target = "id", ignore = true)
	Product dtoGetToProduto (ProdutoGetDto source);

	List<ProdutoGetDto> dtoGetToProduto(List<Product> produtos);
}
