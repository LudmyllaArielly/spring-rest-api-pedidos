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
		validIfTheAttributesOfTheAddressAreEqualToViacep(address);
		validIfAddressAttributesIsblank(address);
	}


	@Transactional
	private void validIfTheAttributesOfTheAddressAreEqualToViacep(Address address) {
		AddressFindViacepDto addressFindViacepDtos = addressFindViacepService
				.findZipCodeByViacep(address.getZipCode());

		validAddressZipCodeEqualsViacep(address, addressFindViacepDtos);
		validAddressStreetEqualsViacep(address, addressFindViacepDtos);
		validAddressComplementEqualsViacep(address, addressFindViacepDtos);
		validAddressDistrictEqualsViacep(address, addressFindViacepDtos);
		validAddressLocaleEqualsViacep(address, addressFindViacepDtos);
		validAddressStateEqualsViacep(address, addressFindViacepDtos);

	}

	private void validAddressZipCodeEqualsViacep(Address address, AddressFindViacepDto addressFindViacepDtos) {

		String isAddressViacepCep = addressFindViacepDtos.getZipCode().replace("-", "");
		String isAddressZipCode = address.getZipCode().replace("-", "");

		if (!isAddressViacepCep.equals(isAddressZipCode)) {
			throw new IllegalArgumentException("Incorrect zip code!");
		}
	}

	private void validAddressStreetEqualsViacep(Address address, AddressFindViacepDto addressFindViacepDtos) {

		boolean isAddressViacepStreetBlank = addressFindViacepDtos.getStreet().isBlank();
		String isAddressViacepStreet = addressFindViacepDtos.getStreet();
		String isAddressStreet = address.getStreet();

		boolean isAddressViacepStreetEqualsAddressStreet =	
				Useful.compareCaseSensitiveAndCheckToQuote(isAddressViacepStreet, isAddressStreet);

		if (!isAddressViacepStreetBlank) {
			if (!isAddressViacepStreet.equalsIgnoreCase(isAddressStreet)) {
				if (!isAddressViacepStreetEqualsAddressStreet) {
					throw new IllegalArgumentException("Incorrect street!");
				}
			}
		}
	}

	private void validAddressComplementEqualsViacep(Address address, AddressFindViacepDto addressFindViacepDtos) {

		boolean isAddressViacepComplementBlank = addressFindViacepDtos.getComplement().isBlank();
		String isAddressViacepComplement = addressFindViacepDtos.getComplement();
		String isAddressComplement = address.getComplement();

		boolean isAddressViacepComplementEqualsAddressComplement = 
				Useful.compareCaseSensitiveAndCheckToQuote(isAddressViacepComplement, isAddressComplement);

		if (!isAddressViacepComplementBlank) {
			if (!isAddressViacepComplement.equalsIgnoreCase(isAddressComplement)) {
				if (!isAddressViacepComplementEqualsAddressComplement) {
					throw new IllegalArgumentException("Incorrect complement!");
				}
			}
		}
	}

	private void validAddressDistrictEqualsViacep(Address address, AddressFindViacepDto addressFindViacepDtos) {

		boolean isAddressViacepDistrictBlank = addressFindViacepDtos.getDistrict().isBlank();
		String isAddressViacepDistrict = addressFindViacepDtos.getDistrict();
		String isAddressDistrict = address.getDistrict();

		boolean isAdressViacepDistrictEqualsAddressDistrict = 
				Useful.compareCaseSensitiveAndCheckToQuote(isAddressViacepDistrict, isAddressDistrict);
				

		if (!isAddressViacepDistrictBlank) {
			if (!isAddressViacepDistrict.equalsIgnoreCase(isAddressDistrict)) {
				if (!isAdressViacepDistrictEqualsAddressDistrict) {
					throw new IllegalArgumentException("Incorrect district!");
				}
			}
		}
	}

	private void validAddressLocaleEqualsViacep(Address address, AddressFindViacepDto addressFindViacepDtos) {

		boolean isAddressViacepLocaleBlank = addressFindViacepDtos.getLocale().isBlank();
		String isAddressViacepLocale = addressFindViacepDtos.getLocale();
		String isAddressLocale = address.getLocale();

		boolean isAddressViacepLocaleEqualsAddressLocale = 
				Useful.compareCaseSensitiveAndCheckToQuote(isAddressViacepLocale, isAddressLocale);

		if (!isAddressViacepLocaleBlank) {
			if (!isAddressViacepLocale.equalsIgnoreCase(isAddressLocale)) {
				if (!isAddressViacepLocaleEqualsAddressLocale) {
					throw new IllegalArgumentException("Incorrect locale");
				}
			}
		}
	}

	private void validAddressStateEqualsViacep(Address address, AddressFindViacepDto addressFindViacepDtos) {

		boolean isAddressViacepUfBlank = addressFindViacepDtos.getUf().isBlank();
		String isAddressViacepUf = addressFindViacepDtos.getUf();
		String isAddressUf = address.getUf();

		boolean isAddressViacepUfEqualsAddressState = 
				Useful.compareCaseSensitiveAndCheckToQuote(isAddressViacepUf, isAddressUf);

		if (!isAddressViacepUfBlank) {
			if (!isAddressViacepUf.equalsIgnoreCase(isAddressUf)) {
				if (!isAddressViacepUfEqualsAddressState) {
					throw new IllegalArgumentException("Incorrect state");
				}
			}
		}
	}
	
	
	private void validIfAddressAttributesIsblank(Address address) {
		boolean isZipCodeBlank = address.getZipCode().isBlank();
		boolean isStreetBlank = address.getStreet().isBlank();
		boolean isComplementBlank = address.getComplement().isBlank();
		boolean isDistrictBlank = address.getDistrict().isBlank();
		boolean isLocaleBlank = address.getLocale().isBlank();
		boolean isUfBlank = address.getUf().isBlank();
		
		if(isZipCodeBlank || isStreetBlank || isComplementBlank || isDistrictBlank
				|| isLocaleBlank || isUfBlank) {
			throw new IllegalArgumentException("There are one or more blank fields!");
		}
	}

}
