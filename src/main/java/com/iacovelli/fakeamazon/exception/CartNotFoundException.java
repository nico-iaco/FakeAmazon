package com.iacovelli.fakeamazon.exception;

import java.util.NoSuchElementException;

/**
 * Exception used when the cart is not found
 */
public class CartNotFoundException extends NoSuchElementException {

	private String msg;

	public CartNotFoundException(String msg) {
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return msg;
	}
}
