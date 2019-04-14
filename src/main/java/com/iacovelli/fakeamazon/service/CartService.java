package com.iacovelli.fakeamazon.service;

import com.iacovelli.fakeamazon.exception.CartNotFoundException;
import com.iacovelli.fakeamazon.model.Cart;
import com.iacovelli.fakeamazon.model.Product;
import com.iacovelli.fakeamazon.model.User;
import com.iacovelli.fakeamazon.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class CartService {

	@Autowired
	private CartRepo cartRepo;

	public Cart getCartFromId(Long id) {
		return cartRepo.getCartById(id).orElseThrow(() -> new CartNotFoundException("Carrello non trovato"));
	}

	public Cart generateCart(User u) {
		Cart cart = new Cart().setUser(u);
		return cartRepo.save(cart);
	}

	public void addProdottoToCart(Long id, Product p) {
		Cart c = getCartFromId(id);
		c.addProduct(p);
		cartRepo.save(c);
	}

	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public Set<Product> getProductsFromCart(Long id) {
		return getCartFromId(id).getProducts();
	}

	public void deleteCart(Cart c) {
		cartRepo.delete(c);
	}

	public void deleteProductFromCart(Long id, Product p) {
		Cart c = getCartFromId(id);
		c.getProducts().remove(p);
		cartRepo.save(c);
	}

	public void emptyCart(Long id) {
		Cart c = getCartFromId(id);
		c.emptyCart();
		cartRepo.save(c);
	}

}
