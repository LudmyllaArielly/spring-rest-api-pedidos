package com.ludmylla.spring.loja.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ludmylla.spring.loja.dto.AddressFindViacepDto;

@Service
public class AddressFindViacepServiceImpl implements AddressFindViacepService {

	@Override
	public AddressFindViacepDto findZipCodeByViacep(String zipCode) {
		validIfViacepAddressZipCodeIsBlank(zipCode);

		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://viacep.com.br/ws/" + zipCode + "/json/";
		ResponseEntity<AddressFindViacepDto> addressFindViacepDto = restTemplate.getForEntity(uri,
				AddressFindViacepDto.class);
		AddressFindViacepDto response = addressFindViacepDto.getBody();

		validIfViacepAddressZipCodeIsNull(response);

		return response;
	}


	private void validIfViacepAddressZipCodeIsNull(AddressFindViacepDto response) {
		boolean isAddressZipCodeOfViacepNull = response.getZipCode() == null;

		if (isAddressZipCodeOfViacepNull) {
			throw new IllegalArgumentException("Zip code does not exist or is incorrect!");
		}
	}

	private void validIfViacepAddressZipCodeIsBlank(String zipCode) {
		boolean isAddressZipCodeOfViacepBlank = zipCode.isBlank();

		if (isAddressZipCodeOfViacepBlank) {
			throw new IllegalArgumentException("Zip cannot be blank!");
		}
	}

}
