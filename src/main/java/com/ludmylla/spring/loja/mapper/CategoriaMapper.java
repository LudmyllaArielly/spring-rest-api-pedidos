package com.ludmylla.spring.loja.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ludmylla.spring.loja.dto.CategoriaDto2;
import com.ludmylla.spring.loja.model.Categoria;

@Mapper
public interface CategoriaMapper {
	
	CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

	@Mapping(target = "id", ignore = true)
	Categoria dtoToCategoria(CategoriaDto2 source);

	

}
