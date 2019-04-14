package com.iacovelli.fakeamazon.api;

import com.iacovelli.fakeamazon.model.Product;
import com.iacovelli.fakeamazon.model.form.Category;
import com.iacovelli.fakeamazon.service.CartService;
import com.iacovelli.fakeamazon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CartService cartService;

	@GetMapping("/subcategory/{category}")
	public List<String> getSubcategory(@PathVariable("category") String category) {
		return Category.valueOf(category).getSubcategory();
	}

	@GetMapping("/product/codice/{id}")
	public List<Product> getProdotto(@PathVariable("id") String id) {
		List<Product> result = new ArrayList<>();
		result.add(productService.getProductById(Long.parseLong(id)));
		return result;
	}

	@GetMapping("/product/descrizione/{desc}")
	public List<Product> getProdottoFromDescrizione(@PathVariable("desc") String desc) {
		return productService.getProductsFromDescription(desc);
	}

	@GetMapping("/product/categoria/{cat}")
	public List<Product> getProdottoFromCategoria(@PathVariable("cat") String cat) {
		return productService.getProductsFromCategoria(cat);
	}

	@GetMapping("/cart/add/{cartId}/{productId}")
	public boolean addProductToCart(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId) {
		Product p = productService.getProductById(productId);
		cartService.addProdottoToCart(cartId, p);
		return true;
	}

	@GetMapping("/cart/{cartId}")
	public Set<Product> getProductsFromCart(@PathVariable("cartId") Long cartId) {
		return cartService.getProductsFromCart(cartId);
	}

	@GetMapping("/cart/delete/{cartId}")
	public void deleteAllProductsFromCart(@PathVariable("cartId") Long cartId) {
		cartService.emptyCart(cartId);
	}

	@GetMapping("/cart/delete/{cartId}/{productId}")
	public void deleteProductFromCart(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId) {
		Product p = productService.getProductById(productId);
		cartService.deleteProductFromCart(cartId, p);
	}


}
