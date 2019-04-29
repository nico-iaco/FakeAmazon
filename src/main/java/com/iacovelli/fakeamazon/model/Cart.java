package com.iacovelli.fakeamazon.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * This is the cart model represented by Cart table inside the Db
 */
@Entity
@Table(name = "Cart")
public class Cart extends BaseEntity<Long> {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id private Long id;

	@OneToOne
	private User user;

	@ManyToMany(targetEntity = Product.class)
	@JoinColumn(name = "id", table = "Prodotti")
	private Set<Product> products = new HashSet<>();

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

	public Set<Product> getProducts() {
		return products;
	}

	public void addProduct(Product p) {
		products.add(p);
	}

	public void emptyCart() {
		this.products = new HashSet<>();
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
