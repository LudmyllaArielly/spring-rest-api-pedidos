package com.ludmylla.spring.loja.resource;

import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<Long> save(@RequestBody PersonInsertDto personInsertDto) {

		Person person = PersonMapper.INSTANCE
				.dtoInsetPerson(personInsertDto);


		Long id = personService.save(person);
		return ResponseEntity.ok(id);

	}
/*
	@PostMapping(path = "/cadastroCliente")
	public ResponseEntity<String> cadastrarCliente(@RequestBody PersonInsertDto pessoaDto) {
		// validaçoes
		// banco verificação
		// salvei
		// retonei
		// exibindo a msg
		return ResponseEntity.ok("cliente cadastro");

	}
*/
	/*@GetMapping(path = "/people")
	public ResponseEntity<List<PersonListDto>> personList() {

		/*List<Person> people = personService.personList();
		List<PersonListDto> list = PersonMapper.INSTANCE
				.dtoListPersonList(people);

//        List<PessoaGetDto> result = new ArrayList<>();
//        for (int i = 0; i < pessoas.size(); i++) {
//            PessoaGetDto pessoaGetDto = new PessoaGetDto();
//            pessoaGetDto.setCpf(pessoas.get(i).getCpf());
//            pessoaGetDto.setName(pessoas.get(i).getName());
//            result.add(pessoaGetDto);
//
//        }

		return ResponseEntity.ok(list);

	}*/

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
