package com.iacovelli.fakeamazon.repo;

import com.iacovelli.fakeamazon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User, String> {

	//Optional<User> findUserByEmailAndPassword(String email, String password);

	Optional<User> findUserByEmail(String email);

}
