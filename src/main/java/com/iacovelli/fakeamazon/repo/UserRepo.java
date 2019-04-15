package com.iacovelli.fakeamazon.repo;

import com.iacovelli.fakeamazon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User, String> {

	/**
	 * This method will return a user identified by @param email
	 * @param email
	 * @return
	 */
	Optional<User> findUserByEmail(String email);

}
