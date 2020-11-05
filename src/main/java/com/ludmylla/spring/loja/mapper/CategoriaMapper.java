package com.ludmylla.spring.loja.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ludmylla.spring.loja.dto.CategoriaDto2;
import com.ludmylla.spring.loja.dto.CategoriaPutDto;
import com.ludmylla.spring.loja.model.Categoria;

@Mapper
public interface CategoriaMapper {
	CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);
	
	@Mapping(target = "id", ignore = true)
	Categoria dtoToCategoria(CategoriaDto2 source);
	
	Categoria dtoPutCategoria(CategoriaPutDto source);
	
	List<CategoriaDto2> ListToCategoriaDto2ToListCategoria(List<Categoria> source);
	
	List<Categoria> ListToCategoriaListCategoriaDto2(List<CategoriaDto2> source);
	
	
	Categoria categoriaToCategoria (Categoria source);
	
	
}