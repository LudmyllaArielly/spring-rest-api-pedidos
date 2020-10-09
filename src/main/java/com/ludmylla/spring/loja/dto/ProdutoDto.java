package com.ludmylla.spring.loja.dto;

public class ProdutoDto {

	private String nome;
	private CategoriaDto categoriaDto;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public CategoriaDto getCategoriaDto() {
		return categoriaDto;
	}

	public void setCategoriaDto(CategoriaDto categoriaDto) {
		this.categoriaDto = categoriaDto;
	}

}
