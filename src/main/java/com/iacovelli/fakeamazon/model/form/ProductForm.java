package com.iacovelli.fakeamazon.model.form;

/**
 * This is the model for the product form
 */
public class ProductForm {

	private Long id;

	private String title;

	private String description;

	private String categoria;

	private String Sottocategoria;

	public Long getId() {
		return id;
	}

	public ProductForm setId(Long id) {
		this.id = id;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public ProductForm setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public ProductForm setDescription(String description) {
		this.description = description;
		return this;
	}

	public String getCategoria() {
		return categoria;
	}

	public ProductForm setCategoria(String categoria) {
		this.categoria = categoria;
		return this;
	}

	public String getSottocategoria() {
		return Sottocategoria;
	}

	public ProductForm setSottocategoria(String sottocategoria) {
		Sottocategoria = sottocategoria;
		return this;
	}
}
