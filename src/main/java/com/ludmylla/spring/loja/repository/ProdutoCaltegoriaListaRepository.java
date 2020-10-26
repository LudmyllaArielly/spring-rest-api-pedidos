package com.ludmylla.spring.loja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ludmylla.spring.loja.model.ProdutoCategoriaLista;

@Repository
@Transactional
public interface ProdutoCaltegoriaListaRepository extends CrudRepository<ProdutoCategoriaLista, Long> {
	
	@Query("select u from ProdutoCategoriaLista u where lower(u.nome) = lower(?1)")
	List<ProdutoCategoriaLista> findByName(String nome);
	
	@Query("select u from ProdutoCategoriaLista u where lower(u.codigo) = lower(?1)")
	List<ProdutoCategoriaLista> findByCodigo(String codigo);
	
}
