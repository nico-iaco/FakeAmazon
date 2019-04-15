package com.iacovelli.fakeamazon.exception;

/**
 * This exception is used when a user is already registered
 */
public class UserAlreadyRegisteredException extends RuntimeException {

	private String msg;

	public UserAlreadyRegisteredException(String msg) {
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return msg;
	}
}
