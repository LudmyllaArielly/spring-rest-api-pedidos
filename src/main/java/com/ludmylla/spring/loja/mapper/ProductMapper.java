package com.ludmylla.spring.loja.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ludmylla.spring.loja.dto.ProductInsertDto;
import com.ludmylla.spring.loja.model.Product;

@Mapper(uses = { CategoryMapper.class})
public interface ProductMapper {
	
	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
	
	Product productToproduct (Product source);
	
	@Mapping(target = "categories", source = "listCategoryInsertDto")
	@Mapping(target = "id", ignore = true)
	Product dtoInsertProduct (ProductInsertDto source);
	
	
}