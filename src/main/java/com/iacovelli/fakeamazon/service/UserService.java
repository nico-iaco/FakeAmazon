package com.iacovelli.fakeamazon.service;

import com.iacovelli.fakeamazon.exception.UserAlreadyRegisteredException;
import com.iacovelli.fakeamazon.exception.UserNotFoundException;
import com.iacovelli.fakeamazon.model.User;
import com.iacovelli.fakeamazon.repo.UserRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

	@Autowired
	private UserRepo repo;

	public boolean register(String email, String password) {
		User u = new User()
				.setId(email)
				.setPassword(hashPassword(password));
		u.setCreatedDate(LocalDateTime.now());
		if (!repo.findUserByEmail(email).isPresent()) {
			repo.save(u);
		} else {
			throw new UserAlreadyRegisteredException("Utente già registrato");
		}
		return true;
	}

	public boolean login(String email, String password) {
		User u = repo.findUserByEmail(email).orElseThrow(() -> new UserNotFoundException("Utente non trovato"));
		return BCrypt.checkpw(password, u.getPassword());
	}


	private String hashPassword(String plainTextPassword){
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}

}
