package com.ludmylla.spring.loja.dto;

public class PersonInsertDto {

	private String name;
	private String cpf;
	private AddressInsertDto addressInsertDto;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public AddressInsertDto getAddressInsertDto() {
		return addressInsertDto;
	}

	public void setAddressInsertDto(AddressInsertDto addressInsertDto) {
		this.addressInsertDto = addressInsertDto;
	}

}
