package com.ludmylla.spring.loja.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ludmylla.spring.loja.dto.AddressFindViacepDto;
import com.ludmylla.spring.loja.service.AddressFindViacepService;

@RestController
@RequestMapping(path = "/address")
public class AddressFindViacepResource {
	
	@Autowired
	private AddressFindViacepService addressFindViacepService;
	
	@GetMapping(path = "/{cep}")
	public ResponseEntity<AddressFindViacepDto> findZipCodeB(@PathVariable("cep") String cep){
		try {
			AddressFindViacepDto addressFindViacepDto = 
					addressFindViacepService.findZipCodeByViacep(cep);
		
			return new ResponseEntity<AddressFindViacepDto>(addressFindViacepDto,HttpStatus.OK);
		}catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
}