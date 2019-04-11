package com.iacovelli.fakeamazon.model;

import javax.persistence.*;

@Entity
@Table(name = "Cart")
public class Cart extends BaseEntity<Long> {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id private Long id;

	@ManyToOne
	private User user;

	@ManyToOne
	private Product product;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Cart setId(Long id) {
		this.id = id;
		return this;
	}

	public User getUser() {
		return user;
	}

	public Cart setUser(User user) {
		this.user = user;
		return this;
	}

	public Product getProduct() {
		return product;
	}

	public Cart setProduct(Product product) {
		this.product = product;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		Cart cart = (Cart) o;

		return id.equals(cart.id);

	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + id.hashCode();
		return result;
	}
}
