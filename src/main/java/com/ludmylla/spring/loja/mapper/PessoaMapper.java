package com.ludmylla.spring.loja.mapper;

import java.util.List;

import com.ludmylla.spring.loja.dto.PessoaDto;
import com.ludmylla.spring.loja.dto.PessoaGetDto;
import com.ludmylla.spring.loja.model.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PessoaMapper {
    PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

    @Mapping(target = "tamanho", source = "altura")
    @Mapping(target = "raca", source = "cor")
    @Mapping(target = "id", ignore = true)
    Pessoa dtoToPessoa(PessoaDto source);


    @Mapping(target = "altura", source = "tamanho")
    @Mapping(target = "cor", source = "raca")
    PessoaGetDto pessoaToPessoaGetDto(Pessoa source);

    List<PessoaGetDto> ListPessoaToListPessoaGetDto(List<Pessoa> source);

}
