package com.ludmylla.spring.loja.dto;

import java.util.List;

public class ProdutoPutDto {
	private Long id;
	private String nome;
	private List<CategoriaDto2> listCategoriaDto2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<CategoriaDto2> getListCategoriaDto2() {
		return listCategoriaDto2;
	}

	public void setListCategoriaDto2(List<CategoriaDto2> listCategoriaDto2) {
		this.listCategoriaDto2 = listCategoriaDto2;
	}

	

}
