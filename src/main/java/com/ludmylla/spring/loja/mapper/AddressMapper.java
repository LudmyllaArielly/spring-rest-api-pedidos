package com.ludmylla.spring.loja.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ludmylla.spring.loja.dto.AddressInsertDto;
import com.ludmylla.spring.loja.model.Address;

@Mapper(uses = { PersonMapper.class})
public interface AddressMapper {
	
	AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);
	
	@Mapping(target = "id", ignore = true)	
	@Mapping(target = "person", ignore = true)	
	Address toAddressInsertDto(AddressInsertDto source);
	
	
}
