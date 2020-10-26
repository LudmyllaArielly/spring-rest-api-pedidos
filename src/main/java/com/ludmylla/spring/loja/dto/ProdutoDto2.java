package com.ludmylla.spring.loja.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class ProdutoDto2 {

	private String nome;
	private String codigo;
	private BigDecimal preco;
	private Integer quantidade;
	private String peso;
	private String unidade;
	private OffsetDateTime dataCadastro;
	private List<CategoriaDto2> listCategoriaDto2;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public OffsetDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(OffsetDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public List<CategoriaDto2> getListCategoriaDto2() {
		return listCategoriaDto2;
	}

	public void setListCategoriaDto2(List<CategoriaDto2> listCategoriaDto2) {
		this.listCategoriaDto2 = listCategoriaDto2;
	}

}
