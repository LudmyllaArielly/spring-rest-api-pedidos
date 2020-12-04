package com.ludmylla.spring.loja.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ludmylla.spring.loja.dto.ProductInsertDto;
import com.ludmylla.spring.loja.dto.ProductListDto;
import com.ludmylla.spring.loja.dto.ProductUpdateDto;
import com.ludmylla.spring.loja.model.Product;

@Mapper(uses = { CategoryMapper.class})
public interface ProductMapper {
	
	ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

	@Mapping(target = "categories", source = "listCategoryInsertDto")
	@Mapping(target = "id", ignore = true)
	Product toProductInsertDto (ProductInsertDto source);
	
	@Mapping(target = "categories", source = "listCategoryInsertDto")
	Product toProductUpdateDto (ProductUpdateDto source);
	
	@Mapping(target = "categories", ignore = true)
	@Mapping(target = "id", ignore = true)
	Product toProductListDto(ProductListDto source);
	
	List<ProductListDto> dtoProductListDto (List<Product> source);
	
	
}