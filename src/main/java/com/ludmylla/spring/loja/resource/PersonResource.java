package com.ludmylla.spring.loja.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ludmylla.spring.loja.dto.AddressFindDto;
import com.ludmylla.spring.loja.dto.PersonInsertDto;
import com.ludmylla.spring.loja.dto.PersonUpdateDto;
import com.ludmylla.spring.loja.mapper.PersonMapper;
import com.ludmylla.spring.loja.model.Person;
import com.ludmylla.spring.loja.service.PersonService;

@RestController
public class PersonResource {

	@Autowired
	private PersonService personService;

	@PostMapping(path = "/people")
	public ResponseEntity<Long> save(@RequestBody PersonInsertDto personInsertDto) {
		
		Person person = PersonMapper.INSTANCE.toPersonInsertDto(personInsertDto);

		Long id = personService.save(person);

		return ResponseEntity.ok(id);

	}
	
	@GetMapping(path = "/people/{cep}")
	public ResponseEntity<AddressFindDto> findCep(@PathVariable("cep") String cep){
		Person person = new Person();
		AddressFindDto addressSave = personService.findCep(person, cep);
		
		
		return new ResponseEntity<AddressFindDto>(addressSave,HttpStatus.OK);
	}
	


	@PutMapping(path = "/people")
	public ResponseEntity<String> update(PersonUpdateDto personUpdateDto) {
		// atulizar
		personService.update(personUpdateDto.getId(), personUpdateDto.getName());

		return ResponseEntity.ok("ok");

	}

	@DeleteMapping(path = "/people")
	public ResponseEntity<String> delete(long id) {

		personService.delete(id);

		return ResponseEntity.ok("ok");

	}

}
