package com.ludmylla.spring.loja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ludmylla.spring.loja.model.Product;

@Repository
@Transactional
public interface ProductRepository extends CrudRepository<Product, Long> {

	@Query("select u from Product u where lower(u.name) = lower(?1)")
	List<Product> findByName(String name);

	@Query("select u from Product u where lower(u.code) = lower(?1)")
	List<Product> findByCode(String code);
}
