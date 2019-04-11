package com.iacovelli.fakeamazon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Prodotti")
public class Product extends BaseEntity<Long> {


	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "title")
	private String name;

	@Column(name = "descrizione")
	private String descrizione;

	@Column(name = "categoria")
	private String categoria;

	@Column(name = "sottocategoria")
	private String sottocategoria;


	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Product setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Product setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public Product setDescrizione(String descrizione) {
		this.descrizione = descrizione;
		return this;
	}

	public String getCategoria() {
		return categoria;
	}

	public Product setCategoria(String categoria) {
		this.categoria = categoria;
		return this;
	}

	public String getSottocategoria() {
		return sottocategoria;
	}

	public Product setSottocategoria(String sottocategoria) {
		this.sottocategoria = sottocategoria;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Product product = (Product) o;

		return id.equals(product.id);

	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + id.hashCode();
		return result;
	}
}
