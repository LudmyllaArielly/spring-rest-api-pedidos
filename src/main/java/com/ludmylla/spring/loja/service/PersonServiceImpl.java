package com.ludmylla.spring.loja.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ludmylla.spring.loja.dto.AddressFindViacepDto;
import com.ludmylla.spring.loja.model.Address;
import com.ludmylla.spring.loja.model.Person;
import com.ludmylla.spring.loja.repository.PersonRepository;
import com.ludmylla.spring.loja.useful.Useful;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private AddressFindViacepService addressFindViacepService;

	@Override
	@Transactional
	public Long save(Person person) {

		validations(person.getAddress());
		Person personSave = personRepository.save(person);
		person.getAddress().setPerson(personSave);
		return personSave.getId();
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

	private void validations(Address address) {
		validIfAttributesOfAddressIsEqualsToViacep(address);
		validIfAddressAttributesIsblank(address);
	}

	@Transactional
	private void validIfAttributesOfAddressIsEqualsToViacep(Address address) {
		AddressFindViacepDto addressFindViacepDtos = 
				addressFindViacepService.findZipCodeByViacep(address.getZipCode());

		validAddressZipCodeEqualsViacep(address, addressFindViacepDtos);

		Useful.validIfAddressIsEqualsViacep(addressFindViacepDtos.getStreet(), address.getStreet());
		Useful.validIfAddressIsEqualsViacep(addressFindViacepDtos.getComplement(), address.getComplement());
		Useful.validIfAddressIsEqualsViacep(addressFindViacepDtos.getDistrict(), address.getDistrict());
		Useful.validIfAddressIsEqualsViacep(addressFindViacepDtos.getLocale(), address.getLocale());
		Useful.validIfAddressIsEqualsViacep(addressFindViacepDtos.getUf(), address.getUf());
	}

	private void validAddressZipCodeEqualsViacep(Address address, AddressFindViacepDto addressFindViacepDtos) {

		String isAddressViacepZipCode = addressFindViacepDtos.getZipCode().replace("-", "");
		String isAddressZipCode = address.getZipCode().replace("-", "");

		if (!isAddressViacepZipCode.equals(isAddressZipCode)) {
			throw new IllegalArgumentException("Incorrect zip code!");
		}
	}

	private void validIfAddressAttributesIsblank(Address address) {
		boolean isZipCodeBlank = address.getZipCode().isBlank();
		boolean isStreetBlank = address.getStreet().isBlank();
		boolean isComplementBlank = address.getComplement().isBlank();
		boolean isDistrictBlank = address.getDistrict().isBlank();
		boolean isLocaleBlank = address.getLocale().isBlank();
		boolean isUfBlank = address.getUf().isBlank();

		if (isZipCodeBlank || isStreetBlank || isComplementBlank || isDistrictBlank || isLocaleBlank || isUfBlank) {
			throw new IllegalArgumentException("There are one or more blank fields!");
		}
	}

}
