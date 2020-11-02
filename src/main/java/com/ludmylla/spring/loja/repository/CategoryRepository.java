package com.ludmylla.spring.loja.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ludmylla.spring.loja.model.Category;

@Repository
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	@Query("select u from Category u where lower(u.name) = lower(?1)")
	List<Category> findByName(String name);

}
