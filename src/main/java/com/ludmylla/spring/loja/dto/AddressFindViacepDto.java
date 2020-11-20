package com.ludmylla.spring.loja.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressFindViacepDto {

	@JsonProperty("cep")
	private String zipCode;
	@JsonProperty("logradouro")
	private String street;
	@JsonProperty("complemento")
	private String complement;
	@JsonProperty("bairro")
	private String district;
	@JsonProperty("localidade")
	private String locale;
	private String uf;

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}
