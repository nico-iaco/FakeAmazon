package com.iacovelli.fakeamazon.repo;

import com.iacovelli.fakeamazon.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product, Long> {

	Optional<Product> getProductById(Long id);

	List<Product> getProductsByDescrizioneContains(String descrizione);

	//@Query("SELECT p FROM Product  as p WHERE p.categoria LIKE %:c%")
	List<Product> findProductsByCategoriaContains(String c);

}
