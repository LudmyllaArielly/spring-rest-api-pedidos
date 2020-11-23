package com.ludmylla.spring.loja.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ludmylla.spring.loja.dto.PersonInsertDto;
import com.ludmylla.spring.loja.dto.PersonListDto;
import com.ludmylla.spring.loja.model.Person;

@Mapper(uses = { AddressMapper.class})
public interface PersonMapper {

	PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "address", source="addressInsertDto")
	Person toPersonInsertDto(PersonInsertDto source);
	

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "address", ignore = true)
	Person toPersonListDto(PersonListDto source);

	List<Person> toListPersonListDto(List<PersonListDto> source);

}
