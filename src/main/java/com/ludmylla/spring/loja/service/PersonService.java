package com.ludmylla.spring.loja.service;

import java.util.List;

import com.ludmylla.spring.loja.dto.AddressFindDto;
import com.ludmylla.spring.loja.model.Person;

public interface PersonService {
	
	
	Long save(Person person);

	List<Person> personList();
	
	AddressFindDto findCep(Person person, String cep);

	void update(Long id, String name);

	void delete(long id);
}
