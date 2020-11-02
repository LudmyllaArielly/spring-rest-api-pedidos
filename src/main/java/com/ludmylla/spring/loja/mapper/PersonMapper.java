package com.ludmylla.spring.loja.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ludmylla.spring.loja.dto.PersonInsertDto;
import com.ludmylla.spring.loja.dto.PersonListDto;
import com.ludmylla.spring.loja.model.Person;

@Mapper
public interface PersonMapper {

	PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

	@Mapping(target = "id", ignore = true)
	Person dtoInsetPerson(PersonInsertDto source);

	@Mapping(target = "id", ignore = true)
	Person dtoListPerson(PersonListDto source);

	List<Person> dtoListPersonList(List<PersonListDto> source);

}
