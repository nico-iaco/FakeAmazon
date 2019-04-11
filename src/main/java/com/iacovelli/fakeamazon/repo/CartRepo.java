package com.iacovelli.fakeamazon.repo;

import com.iacovelli.fakeamazon.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Long> {

}
