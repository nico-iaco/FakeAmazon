package com.iacovelli.fakeamazon.repo;

import com.iacovelli.fakeamazon.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {

	/**
	 * This method will return a product identified by @param id
	 * @param id
	 * @return
	 */
	Optional<Product> getProductById(Long id);

	/**
	 * This method will return a list of products which contains @param descrizione
	 * @param descrizione
	 * @return
	 */
	List<Product> getProductsByDescrizioneContains(String descrizione);

	/**
	 * This method will return a list of products of @param c category
	 * @param c
	 * @return
	 */
	List<Product> findProductsByCategoriaContains(String c);

}
