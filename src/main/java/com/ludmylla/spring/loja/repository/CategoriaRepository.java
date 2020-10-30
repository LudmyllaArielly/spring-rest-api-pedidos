package com.ludmylla.spring.loja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ludmylla.spring.loja.model.Categoria;

@Repository
@Transactional
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
   
	@Query("select u from Categoria u where lower(u.nome) = lower(?1)")
	List<Categoria> findByName(String nome);
	
	@Query("select u from Categoria u where lower(u.nome) like lower(concat('%', ?1, '%'))")
	List<Categoria> findByLikeName( String nome);
}
