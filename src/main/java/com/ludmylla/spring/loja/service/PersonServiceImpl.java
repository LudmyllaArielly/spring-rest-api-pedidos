package com.ludmylla.spring.loja.service;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ludmylla.spring.loja.dto.AddressFindDto;
import com.ludmylla.spring.loja.model.Person;
import com.ludmylla.spring.loja.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Override
	@Transactional
	public Long save(Person person) {
		findCep(person, person.getCep());
		Person personSave = personRepository.save(person);
		return personSave.getId();
	}
	
	@Override
	public AddressFindDto findCep(Person person, String cep) {
		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://viacep.com.br/ws/"+cep+"/json/";
		ResponseEntity<AddressFindDto> addressFindDto = 
				restTemplate.getForEntity(uri, AddressFindDto.class);
		AddressFindDto response = addressFindDto.getBody();

		validAdress(response, person);

		
		person.setBairro(response.getBairro());
		person.setLocalidade(response.getLocalidade());
		person.setUf(response.getUf());
		
		return response;
	}
	
	private void validAdress(AddressFindDto response, Person person) {

		Collator collator = Collator.getInstance(new Locale("pt", "BR"));
		collator.setStrength(Collator.PRIMARY);
		if(collator.compare(person.getLogradouro(), response.getLogradouro()) == 1) {
			person.setLogradouro(response.getLogradouro());
		}
		
		if(collator.compare(person.getComplemento(), response.getComplemento()) == 1) {
			if(!response.getComplemento().isBlank()) {
				person.setComplemento(response.getComplemento());
			}
		}

	}


	@Override
	@Transactional
	public List<Person> personList() {

		List<Person> result = new ArrayList<Person>();
		Iterable<Person> all = personRepository.findAll();
		all.forEach(result::add);

		return result;
	}

	@Override
	@Transactional
	public void update(Long id, String name) {
		Optional<Person> people = personRepository.findById(id);
		Person person = people.get();
		person.setName(name);
		personRepository.save(person);
	}

	@Override
	public void delete(long id) {
		Optional<Person> people = personRepository.findById(id);
		Person person = people.get();
		personRepository.delete(person);
	}




}
