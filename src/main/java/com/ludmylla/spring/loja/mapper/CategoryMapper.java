package com.ludmylla.spring.loja.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ludmylla.spring.loja.dto.CategoryInsertDto;
import com.ludmylla.spring.loja.dto.CategoryUpdateDto;
import com.ludmylla.spring.loja.model.Category;

@Mapper
public interface CategoryMapper {
	
	CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

	@Mapping(target = "id", ignore = true)
	Category toCategoryInsertDto(CategoryInsertDto source);
	
    Category toCategoryUpdateDto(CategoryUpdateDto source);
	
	Category ToCategoria (Category source);

}
