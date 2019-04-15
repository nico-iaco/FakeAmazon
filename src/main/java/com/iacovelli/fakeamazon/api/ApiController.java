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

	/**
	 * This method take in input a category and return the list of its subcategory
	 * @param category
	 * @return
	 */
	@GetMapping("/subcategory/{category}")
	public List<String> getSubcategory(@PathVariable("category") String category) {
		return Category.valueOf(category).getSubcategory();
	}

	/**
	 * This method takes in input the product id and returns a list
	 * of a single Product that matches the id
	 * @param id
	 * @return
	 */
	@GetMapping("/product/codice/{id}")
	public List<Product> getProdotto(@PathVariable("id") String id) {
		List<Product> result = new ArrayList<>();
		result.add(productService.getProductById(Long.parseLong(id)));
		return result;
	}

	/**
	 * This method takes in input a string description and returns
	 * a list with all products that matches the description
	 * @param desc
	 * @return
	 */
	@GetMapping("/product/descrizione/{desc}")
	public List<Product> getProdottoFromDescrizione(@PathVariable("desc") String desc) {
		return productService.getProductsFromDescription(desc);
	}

	/**
	 * This method takes in input a string category and returns
	 * a list with all products in that category
	 * @param cat
	 * @return
	 */
	@GetMapping("/product/categoria/{cat}")
	public List<Product> getProdottoFromCategoria(@PathVariable("cat") String cat) {
		return productService.getProductsFromCategoria(cat);
	}

	/**
	 * This method returns a list with all products in DB
	 * @return
	 */
	@GetMapping("/product")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	/**
	 * This method takes in input a cartId and a productId
	 * and then add the product into the cart
	 * @param cartId
	 * @param productId
	 * @return
	 */
	@GetMapping("/cart/add/{cartId}/{productId}")
	public boolean addProductToCart(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId) {
		Product p = productService.getProductById(productId);
		cartService.addProdottoToCart(cartId, p);
		return true;
	}

	/**
	 * This method takes in input the cartId and returns
	 * a list with all products into that cart
	 * @param cartId
	 * @return
	 */
	@GetMapping("/cart/{cartId}")
	public Set<Product> getProductsFromCart(@PathVariable("cartId") Long cartId) {
		return cartService.getProductsFromCart(cartId);
	}

	/**
	 * This method will delete all products inside the cart
	 * @param cartId
	 */
	@GetMapping("/cart/delete/{cartId}")
	public void deleteAllProductsFromCart(@PathVariable("cartId") Long cartId) {
		cartService.emptyCart(cartId);
	}

	/**
	 * This method will delete the product from the cart
	 * @param cartId
	 * @param productId
	 */
	@GetMapping("/cart/delete/{cartId}/{productId}")
	public void deleteProductFromCart(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId) {
		Product p = productService.getProductById(productId);
		cartService.deleteProductFromCart(cartId, p);
	}


}
