package com.ludmylla.spring.loja.service;

import com.ludmylla.spring.loja.dto.AddressFindViacepDto;

public interface AddressFindViacepService {

	AddressFindViacepDto findZipCodeByViacep(String zipCode);

}
