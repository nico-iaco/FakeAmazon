package com.iacovelli.fakeamazon.service;

import com.iacovelli.fakeamazon.model.Cart;
import com.iacovelli.fakeamazon.repo.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

	@Autowired
	private CartRepo cartRepo;

	//TODO: Implementare le GET per ottenere gli elementi nel carrello dato un utente

	public void addProdottoToCart(Cart c) {
		cartRepo.save(c);
	}

	//TODO: Far passare a questo metodo non l'oggetto di Cart ma le chiavi primarie di User e Product
	public void deleteProdottoFromCart(Cart c) {
		cartRepo.delete(c);
	}

}
