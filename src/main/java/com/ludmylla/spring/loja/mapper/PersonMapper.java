package com.ludmylla.spring.loja.mapper;

import java.util.List;

import com.ludmylla.spring.loja.dto.PersonInsertDto;
import com.ludmylla.spring.loja.dto.PersonListDto;
import com.ludmylla.spring.loja.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
	
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "raca", source = "cor")
    @Mapping(target = "tamanho", source = "altura")
    Person toPersonInsertDto(PersonInsertDto source);
    

    @Mapping(target = "raca", source = "cor")
    @Mapping(target = "tamanho", source = "altura")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "senha", ignore = true)
    Person toPersonListDto(PersonListDto source);
    

    @Mapping(target = "raca", source = "cor")
    @Mapping(target = "tamanho", source = "altura")
    List<Person> toListPersonListDto(List<PersonListDto> source);

}
