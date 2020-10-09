package com.ludmylla.spring.loja.service;

import java.util.List;

import com.ludmylla.spring.loja.model.Pessoa;

public interface PessoaService {

    Long salvar(Pessoa pessoa);

    List<Pessoa> buscar();

    void atualizar(Long id, String name);

    void deltar(long id);
}
