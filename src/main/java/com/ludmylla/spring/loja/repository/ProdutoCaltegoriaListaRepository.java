package com.ludmylla.spring.loja.repository;

import com.ludmylla.spring.loja.model.Produto;
import com.ludmylla.spring.loja.model.ProdutoCategoriaLista;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProdutoCaltegoriaListaRepository extends CrudRepository<ProdutoCategoriaLista, Long> {

}
