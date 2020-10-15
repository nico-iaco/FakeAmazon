package com.iacovelli.fakeamazon.service;

import com.iacovelli.fakeamazon.exception.CartNotFoundException;
import com.iacovelli.fakeamazon.exception.UserNotFoundException;
import com.iacovelli.fakeamazon.model.Cart;
import com.iacovelli.fakeamazon.model.Product;
import com.iacovelli.fakeamazon.model.User;
import com.iacovelli.fakeamazon.repo.CartRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class CartService {

	private final CartRepo cartRepo;
	private final UserService userService;

	public CartService(CartRepo cartRepo, UserService userService) {
		this.cartRepo = cartRepo;
		this.userService = userService;
	}

	/**
	 * This method will return a cart identified by @param id
	 * @param id
	 * @return
	 * @throws CartNotFoundException
	 */
	public Cart getCartFromId(Long id) throws CartNotFoundException {
		return cartRepo.getCartById(id).orElseThrow(() -> new CartNotFoundException("Carrello non trovato"));
	}

	public boolean userHasCart(User u) {
		return cartRepo.getCartByUser(u).isPresent();
	}

	/**
	 * This method will generate a cart for @param u
	 * @param u
	 * @return
	 */
	public Cart generateCart(User u) {
		Cart cart = new Cart().setUser(u);
		return cartRepo.save(cart);
	}

	@Transactional
	public Long generateCartIfEmpty(String user, String pwd) throws UserNotFoundException {
		if (!userService.login(user, pwd)) {
			throw new UserNotFoundException("Credenziali non corrette");
		}
		User u = userService.getUser(user, pwd);
		Long id = userHasCart(u) ? cartRepo.getCartByUser(u).orElseThrow(() -> new UserNotFoundException("Utente non trovato")).getId() : generateCart(u).getId();
		return id;
	}

	/**
	 * This method will add @param p into cart identified by @param id
	 * @param id
	 * @param p
	 */
	@Transactional
	public void addProdottoToCart(Long id, Product p) {
		Cart c = getCartFromId(id);
		c.addProduct(p);
		cartRepo.save(c);
	}

	/**
	 * This method will return all products from cart identified by @param id
	 * @param id
	 * @return
	 */
	public Set<Product> getProductsFromCart(Long id) {
		return getCartFromId(id).getProducts();
	}

	/**
	 * This method will delete @param c
	 * @param c
	 */
	public void deleteCart(Cart c) {
		cartRepo.delete(c);
	}

	/**
	 * This method will delete @param p from cart identified by @param id
	 * @param id
	 * @param p
	 */
	public void deleteProductFromCart(Long id, Product p) {
		Cart c = getCartFromId(id);
		c.getProducts().remove(p);
		cartRepo.save(c);
	}

	/**
	 * This method will delete all products from cart identified by @param id
	 * @param id
	 */
	public void emptyCart(Long id) {
		Cart c = getCartFromId(id);
		c.emptyCart();
		cartRepo.save(c);
	}

}
