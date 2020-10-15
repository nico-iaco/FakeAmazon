package com.iacovelli.fakeamazon.service;

import com.iacovelli.fakeamazon.model.Product;
import com.iacovelli.fakeamazon.model.form.Category;
import com.iacovelli.fakeamazon.model.form.ProductForm;
import com.iacovelli.fakeamazon.repo.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

	private final ProductRepo repo;

	public ProductService(ProductRepo repo) {
		this.repo = repo;
	}

	/**
	 * This method will return a product identified by @param id
	 * @param id The identifier of the product
	 * @return Return the product identified by @param id
	 */
	public Product getProductById(Long id) {
		return repo.getProductById(id).orElseThrow(() -> new NoSuchElementException("Nessun prodotto trovato"));
	}

	/**
	 * This method will return a list of products with description which matches @param description
	 * @param description A piece of description of a product
	 * @return Return the list of the product that matches the desription
	 */
	public List<Product> getProductsFromDescription(String description) {
		return repo.getProductsByDescrizioneContains(description);
	}

	/**
	 * This method will return a list of products with category matches @param category
	 * @param category The identifier of the products
	 * @return Return the list of the products that matches the descriton
	 */
	public List<Product> getProductsFromCategoria(String category) {
		return repo.findProductsByCategoriaContains(category);
	}

	/**
	 * This method will return a list of all products into DB
	 * @return Return all products from Database
	 */
	public List<Product> getAllProducts() {
		return repo.findAll();
	}

	/**
	 * This method will save a product into DB
	 * @param p The product to safe
	 * @return Return if the task has been execute successfully
	 */
	public boolean saveProduct(ProductForm p) {
		try {
			Product product = new Product()
					.setId(p.getId())
					.setName(p.getTitle())
					.setDescrizione(p.getDescription())
					.setCategoria(Category.valueOf(p.getCategoria()))
					.setSottocategoria(p.getSottocategoria());
			repo.save(product);
		}catch (Exception e) {
			return false;
		}
		return true;
	}

}
