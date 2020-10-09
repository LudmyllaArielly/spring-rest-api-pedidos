package com.ludmylla.spring.loja.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ludmylla.spring.loja.model.Produto;

@Repository
@Transactional
public interface ProdutoRepository extends CrudRepository<Produto, Long> {

}
