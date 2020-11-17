package com.ludmylla.spring.loja.resource;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<String> save(@RequestBody PersonInsertDto personInsertDto) {
		try {
		Person person = PersonMapper.INSTANCE.toPersonInsertDto(personInsertDto);

		Long id = personService.save(person);

		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new Date() + " Person added, id: " + id);
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Date() + " Failed to add: " + e.getMessage());
		}

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
