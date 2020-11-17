package com.ludmylla.spring.loja.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ludmylla.spring.loja.dto.AddressFindViacepDto;

@Service
public class AddressFindViacepServiceImpl implements AddressFindViacepService {

	@Override
	public AddressFindViacepDto findZipCodeByViacep(String cep) {
		validIfViacepAddressZipCodeIsBlank(cep);

		RestTemplate restTemplate = new RestTemplate();
		String uri = "http://viacep.com.br/ws/" + cep + "/json/";
		ResponseEntity<AddressFindViacepDto> addressFindViacepDto = restTemplate.getForEntity(uri,
				AddressFindViacepDto.class);
		AddressFindViacepDto response = addressFindViacepDto.getBody();

		validIfViacepAddressZipCodeIsNull(response);

		return response;
	}


	private void validIfViacepAddressZipCodeIsNull(AddressFindViacepDto response) {
		boolean isAddressZipCodeOfViacepNull = response.getCep() == null;

		if (isAddressZipCodeOfViacepNull) {
			throw new IllegalArgumentException("Zip code does not exist or is incorrect!");
		}
	}

	private void validIfViacepAddressZipCodeIsBlank(String cep) {
		boolean isAddressZipCodeOfViacepBlank = cep.isBlank();

		if (isAddressZipCodeOfViacepBlank) {
			throw new IllegalArgumentException("Zip cannot be blank!");
		}
	}

}
