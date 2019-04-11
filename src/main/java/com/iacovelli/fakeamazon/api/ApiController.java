package com.iacovelli.fakeamazon.api;

import com.iacovelli.fakeamazon.model.Product;
import com.iacovelli.fakeamazon.model.form.Category;
import com.iacovelli.fakeamazon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private ProductService service;

	@GetMapping("/subcategory/{category}")
	public List<String> getSubcategory(@PathVariable("category") String category) {
		return Category.valueOf(category).getSubcategory();
	}

	@GetMapping("/product/codice/{id}")
	public List<Product> getProdotto(@PathVariable String id) {
		List<Product> result = new ArrayList<>();
		result.add(service.getProductById(Long.parseLong(id)));
		return result;
	}

	@GetMapping("/product/descrizione/{desc}")
	public List<Product> getProdottoFromDescrizione(@PathVariable String desc) {
		return service.getProductsFromDescription(desc);
	}

	@GetMapping("/product/categoria/{cat}")
	public List<Product> getProdottoFromCategoria(@PathVariable String cat) {
		return service.getProductsFromCategoria(cat);
	}


}
