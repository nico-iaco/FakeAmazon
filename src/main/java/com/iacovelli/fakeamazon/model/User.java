package com.iacovelli.fakeamazon.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Utente")
public class User extends BaseEntity<String> {

	@Id
	@Column(name = "email")
	@NotEmpty @NotNull
	private String email;

	@Column(name = "password")
	private String password;

	@Override
	public String getId() {
		return email;
	}

	@Override
	public User setId(String email) {
		this.email = email;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		return email.equals(user.email);

	}

	@Override
	public int hashCode() {
		return email.hashCode();
	}
}
