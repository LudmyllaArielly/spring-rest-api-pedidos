package com.ludmylla.spring.loja.repository;


import com.ludmylla.spring.loja.model.Pessoa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PessoRepository extends CrudRepository<Pessoa, Long> {

}
