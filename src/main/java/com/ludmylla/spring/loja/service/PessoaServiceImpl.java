package com.ludmylla.spring.loja.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import com.ludmylla.spring.loja.model.Pessoa;
import com.ludmylla.spring.loja.repository.PessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoRepository repository;


    @Override
    @Transactional
    public Long salvar(Pessoa pessoa) {

        Pessoa pessoaSava = repository.save(pessoa);

        return pessoaSava.getId();
    }

    @Override
    @Transactional
    public List<Pessoa> buscar() {

        List<Pessoa> result = new ArrayList<Pessoa>();
        Iterable<Pessoa> all = repository.findAll();
        all.forEach(result::add);

        return result;
    }

    @Override
    @Transactional
    public void atualizar(Long id, String name) {
        Optional<Pessoa> pessoas = repository.findById(id);
        Pessoa pessoa = pessoas.get();
        pessoa.setName(name);
        repository.save(pessoa);
    }

    @Override
    public void deltar(long id) {
        Optional<Pessoa> pessoas = repository.findById(id);
        Pessoa pessoa = pessoas.get();
        repository.delete(pessoa);
    }
}
