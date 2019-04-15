package com.iacovelli.fakeamazon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

	/**
	 * This method will show the cart page
	 * @return
	 */
	@GetMapping("/cart")
	public String showCart() {
		return "cart";
	}

}
