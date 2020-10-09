package com.ludmylla.spring.loja.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ludmylla.spring.loja.model.Categoria;

@Repository
@Transactional
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {
	
	@Query("select u from Categoria u where lower(u.nome) like lower(concat('%', ?1, '%'))")
	Optional<Categoria> findByNomeCat(String nome);
	
	@Query("select u from Categoria u where lower(u.nome) like lower(concat('%', ?1, '%'))")
	Iterable<Categoria> findByNome(String nome);
}
