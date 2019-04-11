package com.iacovelli.fakeamazon.model.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserForm {

	@NotEmpty
	@Email
	private String username;

	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")
	private String password;

	public String getUsername() {
		return username;
	}

	public UserForm setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UserForm setPassword(String password) {
		this.password = password;
		return this;
	}
}
