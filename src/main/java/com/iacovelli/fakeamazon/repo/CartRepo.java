package com.iacovelli.fakeamazon.repo;

import com.iacovelli.fakeamazon.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepo extends JpaRepository<Cart, Long> {

	Optional<Cart> getCartById(Long id);

}
