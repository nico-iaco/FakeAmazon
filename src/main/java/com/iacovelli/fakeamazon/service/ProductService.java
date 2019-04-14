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

	public Product getProductById(Long id) {
		return repo.getProductById(id).orElseThrow(() -> new NoSuchElementException("Nessun prodotto trovato"));
	}

	public List<Product> getProductsFromDescription(String description) {
		return repo.getProductsByDescrizioneContains(description);
	}

	public List<Product> getProductsFromCategoria(String category) {
		return repo.findProductsByCategoriaContains(category);
	}

	public List<Product> getAllProducts() {
		return repo.findAll();
	}

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
