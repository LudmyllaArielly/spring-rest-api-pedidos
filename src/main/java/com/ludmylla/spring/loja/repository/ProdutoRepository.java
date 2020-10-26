package com.ludmylla.spring.loja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ludmylla.spring.loja.model.Categoria;
import com.ludmylla.spring.loja.model.Produto;

@Repository
@Transactional
public interface ProdutoRepository extends CrudRepository<Produto, Long> {

	
    @Query("select u from Categoria u where lower(u.nome) = lower(?1)")
    List<Categoria> findByName2(String nome);
}
