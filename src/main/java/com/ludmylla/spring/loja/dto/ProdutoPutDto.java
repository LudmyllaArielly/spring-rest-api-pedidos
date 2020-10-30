package com.ludmylla.spring.loja.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoPutDto {
	private Long id;
	private String name;
	private String code;
	private BigDecimal price;
	private Integer quantity;

	private List<CategoriaDto2> listCategoriaDto2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<CategoriaDto2> getListCategoriaDto2() {
		return listCategoriaDto2;
	}

	public void setListCategoriaDto2(List<CategoriaDto2> listCategoriaDto2) {
		this.listCategoriaDto2 = listCategoriaDto2;
	}

}
