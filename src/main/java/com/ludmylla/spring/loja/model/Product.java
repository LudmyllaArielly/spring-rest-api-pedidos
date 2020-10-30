package com.ludmylla.spring.loja.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name", name = "name_uk"))
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "O nome não pode ser vazio!")
	private String name;

	@NotBlank(message = "O código não pode ser vazio!")
	@Column(unique = true)
	private String code;

	@Column(nullable = false)	
	@DecimalMin(value = "0.01", message = "Preço tem que ser maior que 0")
	@Digits(integer = 3, fraction = 2, message = "Preço: apenas centenas e 2 casas após o ponto.")
	private BigDecimal price;
	
	@DecimalMin(value = "0.01", message = "Quantidade tem que ser maior que 0")
	@NotNull(message = "A quantidade não pode ser nulo!")
	private Integer quantity;
	
	@NotNull
	@ManyToMany
	@JoinColumn(name = "CATEGORIA_ID")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Categoria> categoria;

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

	public List<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<Categoria> categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProdutoCategoriaLista [id=" + id + ", name=" + name + ", code=" + code + ", price=" + price
				+ ", quantity=" + quantity + ", categoria=" + categoria + "]";
	}

}
