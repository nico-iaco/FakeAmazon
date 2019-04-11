package com.iacovelli.fakeamazon.service;

import com.iacovelli.fakeamazon.model.User;
import com.iacovelli.fakeamazon.repo.UserRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class UserService {

	@Autowired
	private UserRepo repo;

	public boolean register(String email, String password) {
		User u = new User()
				.setId(email)
				.setPassword(hashPassword(password));
		u.setCreatedDate(LocalDateTime.now());
		try {
			repo.save(u);
		}catch (Exception e){
			//TODO: Al posto di ritornare false è meglio lanciare un'eccezione custom per avvertire della mancata registrazione
			return false;
		}
		return true;
	}

	public boolean login(String email, String password) {
		User u = repo.findUserByEmail(email).orElse(null);
		if (u == null) {
			return false;
		}
		return BCrypt.checkpw(password, u.getPassword());
	}


	private String hashPassword(String plainTextPassword){
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}

}
