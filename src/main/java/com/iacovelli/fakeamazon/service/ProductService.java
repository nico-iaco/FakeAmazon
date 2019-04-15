package com.iacovelli.fakeamazon.service;

import com.iacovelli.fakeamazon.model.Product;
import com.iacovelli.fakeamazon.model.form.ProductForm;
import com.iacovelli.fakeamazon.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

	@Autowired
	private ProductRepo repo;

	/**
	 * This method will return a product identified by @param id
	 * @param id
	 * @return
	 */
	public Product getProductById(Long id) {
		return repo.getProductById(id).orElseThrow(() -> new NoSuchElementException("Nessun prodotto trovato"));
	}

	/**
	 * This method will return a list of products with description which matches @param description
	 * @param description
	 * @return
	 */
	public List<Product> getProductsFromDescription(String description) {
		return repo.getProductsByDescrizioneContains(description);
	}

	/**
	 * This method will return a list of products with category matches @param category
	 * @param category
	 * @return
	 */
	public List<Product> getProductsFromCategoria(String category) {
		return repo.findProductsByCategoriaContains(category);
	}

	/**
	 * This method will return a list of all products into DB
	 * @return
	 */
	public List<Product> getAllProducts() {
		return repo.findAll();
	}

	/**
	 * This method will save a product into DB
	 * @param p
	 * @return
	 */
	public boolean saveProduct(ProductForm p) {
		try {
			Product product = new Product()
					.setId(p.getId())
					.setName(p.getTitle())
					.setDescrizione(p.getDescription())
					.setCategoria(p.getCategoria().toString())
					.setSottocategoria(p.getSottocategoria());
			repo.save(product);
		}catch (Exception e) {
			return false;
		}
		return true;
	}

}
