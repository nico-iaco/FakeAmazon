package com.iacovelli.fakeamazon.api;

import com.iacovelli.fakeamazon.model.Product;
import com.iacovelli.fakeamazon.model.form.Category;
import com.iacovelli.fakeamazon.service.CartService;
import com.iacovelli.fakeamazon.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CartService cartService;

	/**
	 * This method take in input a category and return the list of its subcategory
	 * @param category Category from which get the subcategory's list
	 * @return Return the subcategories of that category
	 */
	@GetMapping("/subcategory/{category}")
	public List<String> getSubcategory(@PathVariable("category") String category) {
		return Category.valueOf(category).getSubcategory();
	}

	/**
	 * This method takes in input the product id and returns a list
	 * of a single Product that matches the id
	 * @param id The id of that product
	 * @return Return the product
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
	 * @param desc A piece of product's description
	 * @return Return the list with all products that matches the description
	 */
	@GetMapping("/product/descrizione/{desc}")
	public List<Product> getProdottoFromDescrizione(@PathVariable("desc") String desc) {
		return productService.getProductsFromDescription(desc);
	}

	/**
	 * This method takes in input a string category and returns
	 * a list with all products in that category
	 * @param cat String category
	 * @return Return a list with all products in that category
	 */
	@GetMapping("/product/categoria/{cat}")
	public List<Product> getProdottoFromCategoria(@PathVariable("cat") String cat) {
		return productService.getProductsFromCategoria(cat);
	}

	/**
	 * This method returns a list with all products in DB
	 * @return Return all products
	 */
	@GetMapping("/product")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	/**
	 * This method takes in input a cartId and a productId
	 * and then add the product into the cart
	 * @param cartId The identifier of the cart
	 * @param productId The identifier of the product
	 * @return Return if the task has been completed successfully
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
	 * @param cartId The identifier of the cart
	 * @return Return the products inside the cart
	 */
	@GetMapping("/cart/{cartId}")
	public Set<Product> getProductsFromCart(@PathVariable("cartId") Long cartId) {
		return cartService.getProductsFromCart(cartId);
	}

	/**
	 * This method will delete all products inside the cart
	 * @param cartId The identifier of the cart
	 */
	@GetMapping("/cart/delete/{cartId}")
	public void deleteAllProductsFromCart(@PathVariable("cartId") Long cartId) {
		cartService.emptyCart(cartId);
	}

	/**
	 * This method will delete the product from the cart
	 * @param cartId The identifier of the cart
	 * @param productId The identifier of the product
	 */
	@GetMapping("/cart/delete/{cartId}/{productId}")
	public void deleteProductFromCart(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId) {
		Product p = productService.getProductById(productId);
		cartService.deleteProductFromCart(cartId, p);
	}


}
