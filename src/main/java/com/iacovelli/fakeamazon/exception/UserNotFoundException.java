package com.iacovelli.fakeamazon.exception;

import java.util.NoSuchElementException;

public class UserNotFoundException extends NoSuchElementException {

	private String msg;

	public UserNotFoundException(String msg) {
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return msg;
	}
}
