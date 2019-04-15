package com.iacovelli.fakeamazon.service;

import com.iacovelli.fakeamazon.exception.UserAlreadyRegisteredException;
import com.iacovelli.fakeamazon.exception.UserNotFoundException;
import com.iacovelli.fakeamazon.model.User;
import com.iacovelli.fakeamazon.repo.UserRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepo repo;

	@Autowired
	private CartService service;

	public boolean register(String email, String password) throws UserAlreadyRegisteredException {
		User u = new User()
				.setId(email)
				.setPassword(hashPassword(password));
		if (!repo.findUserByEmail(email).isPresent()) {
			repo.save(u);
		} else {
			throw new UserAlreadyRegisteredException("Utente già registrato");
		}
		return true;
	}

	public boolean login(String email, String password) {
		User u = repo.findUserByEmail(email).orElse(null);
		return u != null && BCrypt.checkpw(password, u.getPassword());
	}

	@Transactional
	public Long generateCartIfEmpty(String id, String pwd) throws UserNotFoundException{
		if (!login(id, pwd)) {
			throw new UserNotFoundException("Credenziali non corrette");
		}
		User u = repo.findUserByEmail(id).orElseThrow(() -> new UserNotFoundException("Utente non trovato"));
		if (u.getCart() == null) {
			u.setCart(service.generateCart(u));
			u = updateUser(u);
		}
		return u.getCart().getId();
	}

	public User updateUser(User u) {
		return repo.save(u);
	}


	private String hashPassword(String plainTextPassword){
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
	}

}
