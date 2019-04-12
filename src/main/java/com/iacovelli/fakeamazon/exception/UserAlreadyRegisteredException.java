package com.iacovelli.fakeamazon.exception;

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
