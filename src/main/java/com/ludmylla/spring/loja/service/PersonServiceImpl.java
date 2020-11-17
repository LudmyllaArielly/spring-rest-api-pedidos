package com.ludmylla.spring.loja.service;

import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ludmylla.spring.loja.dto.AddressFindViacepDto;
import com.ludmylla.spring.loja.model.Address;
import com.ludmylla.spring.loja.model.Person;
import com.ludmylla.spring.loja.repository.PersonRepository;

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

		String isAddressViacepCep = addressFindViacepDtos.getCep().replace("-", "");
		String isAddressZipCode = address.getZipCode().replace("-", "");

		if (!isAddressViacepCep.equals(isAddressZipCode)) {
			throw new IllegalArgumentException("Incorrect zip code!");
		}
	}

	private void validAddressStreetEqualsViacep(Address address, AddressFindViacepDto addressFindViacepDtos) {

		boolean isAddressViacepLogradouroBlank = addressFindViacepDtos.getLogradouro().isBlank();
		String isAddressViacepLogradouro = addressFindViacepDtos.getLogradouro();
		String isAddressStreet = address.getStreet();

		boolean isAddressViacepLogradouroEqualsAddressStreet = Pattern
				.compile(Pattern.quote(removeAccents(isAddressViacepLogradouro)), Pattern.CASE_INSENSITIVE)
				.matcher(removeAccents(isAddressStreet)).find();

		if (!isAddressViacepLogradouroBlank) {
			if (!isAddressViacepLogradouro.equalsIgnoreCase(isAddressStreet)) {
				if (!isAddressViacepLogradouroEqualsAddressStreet) {
					throw new IllegalArgumentException("Incorrect street!");
				}
			}
		}
	}

	private void validAddressComplementEqualsViacep(Address address, AddressFindViacepDto addressFindViacepDtos) {

		boolean isAddressViacepComplementoBlank = addressFindViacepDtos.getComplemento().isBlank();
		String isAddressViacepComplemento = addressFindViacepDtos.getComplemento();
		String isAddressComplement = address.getComplement();

		boolean isAddressViacepComplementoEqualsAddressComplement = Pattern
				.compile(Pattern.quote(removeAccents(isAddressViacepComplemento)), Pattern.CASE_INSENSITIVE)
				.matcher(removeAccents(isAddressComplement)).find();

		if (!isAddressViacepComplementoBlank) {
			if (!isAddressViacepComplemento.equalsIgnoreCase(isAddressComplement)) {
				if (!isAddressViacepComplementoEqualsAddressComplement) {
					throw new IllegalArgumentException("Incorrect complement!");
				}
			}
		}
	}

	private void validAddressDistrictEqualsViacep(Address address, AddressFindViacepDto addressFindViacepDtos) {

		boolean isAddressViacepBairroBlank = addressFindViacepDtos.getBairro().isBlank();
		String isAddressViacepBairro = addressFindViacepDtos.getBairro();
		String isAddressDistrict = address.getDistrict();

		boolean isAdressViacepBairroEqualsAddressDistrict = Pattern
				.compile(Pattern.quote(removeAccents(isAddressViacepBairro)), Pattern.CASE_INSENSITIVE)
				.matcher(removeAccents(isAddressDistrict)).find();

		if (!isAddressViacepBairroBlank) {
			if (!isAddressViacepBairro.equalsIgnoreCase(isAddressDistrict)) {
				if (!isAdressViacepBairroEqualsAddressDistrict) {
					throw new IllegalArgumentException("Incorrect district!");
				}
			}
		}
	}

	private void validAddressLocaleEqualsViacep(Address address, AddressFindViacepDto addressFindViacepDtos) {

		boolean isAddressViacepLocalidadeBlank = addressFindViacepDtos.getLocalidade().isBlank();
		String isAddressViacepLocalidade = addressFindViacepDtos.getLocalidade();
		String isAddressLocale = address.getLocale();

		boolean isAddressViacepLocalidadeEqualsAddressLocale = Pattern
				.compile(Pattern.quote(removeAccents(isAddressViacepLocalidade)), Pattern.CASE_INSENSITIVE)
				.matcher(removeAccents(isAddressLocale)).find();

		if (!isAddressViacepLocalidadeBlank) {
			if (!isAddressViacepLocalidade.equalsIgnoreCase(isAddressLocale)) {
				if (!isAddressViacepLocalidadeEqualsAddressLocale) {
					throw new IllegalArgumentException("Incorrect locale");
				}
			}
		}
	}

	private void validAddressStateEqualsViacep(Address address, AddressFindViacepDto addressFindViacepDtos) {

		boolean isAddressViacepUfBlank = addressFindViacepDtos.getUf().isBlank();
		String isAddressViacepUf = addressFindViacepDtos.getUf();
		String isAddressState = address.getState();

		boolean isAddressViacepUfEqualsAddressState = Pattern
				.compile(Pattern.quote(removeAccents(isAddressViacepUf)), Pattern.CASE_INSENSITIVE)
				.matcher(removeAccents(isAddressState)).find();

		if (!isAddressViacepUfBlank) {
			if (!isAddressViacepUf.equalsIgnoreCase(isAddressState)) {
				if (!isAddressViacepUfEqualsAddressState) {
					throw new IllegalArgumentException("Incorrect state");
				}
			}
		}
	}

	private String removeAccents(String text) {
		return text == null ? null
				: Normalizer.normalize(text, Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}
	
	private void validIfAddressAttributesIsblank(Address address) {
		boolean isZipCodeBlank = address.getZipCode().isBlank();
		boolean isStreetBlank = address.getStreet().isBlank();
		boolean isComplementBlank = address.getComplement().isBlank();
		boolean isDistrictBlank = address.getDistrict().isBlank();
		boolean isLocaleBlank = address.getLocale().isBlank();
		boolean isStateBlank = address.getLocale().isBlank();
		
		if(isZipCodeBlank || isStreetBlank || isComplementBlank || isDistrictBlank
				|| isLocaleBlank || isStateBlank) {
			throw new IllegalArgumentException("There are one or more blank fields!");
		}
	}

}
