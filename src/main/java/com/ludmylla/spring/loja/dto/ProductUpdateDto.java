package com.ludmylla.spring.loja.dto;

import java.math.BigDecimal;
import java.util.List;

public class ProductUpdateDto {

	private Long id;
	private String name;
	private String code;
	private Integer quantity;
	private BigDecimal price;
	private List<CategoryInsertDto> listCategoryInsertDto;

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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<CategoryInsertDto> getListCategoryInsertDto() {
		return listCategoryInsertDto;
	}

	public void setListCategoryInsertDto(List<CategoryInsertDto> listCategoryInsertDto) {
		this.listCategoryInsertDto = listCategoryInsertDto;
	}

}
