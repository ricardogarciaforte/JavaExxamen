package br.com.devmedia.pocket.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.devmedia.pocket.entity.User;
import br.com.devmedia.pocket.repository.UserRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserService {
	
	private static final Logger LOGGER = Logger.getLogger(UserService.class);

	@Autowired
	private UserRepository repository;

    public User findUserByEmail(String email) {
		
		LOGGER.debug("Finding user by email{" + email + "}");
		
		return repository.findByEmail(email);
	}

	public User findById(Long id) {

		LOGGER.debug("Finding user id{" + id + "}");

		return repository.findOne(id);
	}

	public List<User> findUsers() {
		
		LOGGER.debug("Get All Users");
		
		return repository.findAll();
	}

	public void createUser(User user) {
		
		LOGGER.debug("Saving user{" + user.getEmail() + "}");
		
		String passwordHash = new BCryptPasswordEncoder().encode(user.getPassword());
		
		user.setPassword(passwordHash);
		
		repository.save(user);		
	}

	public void registerLastLogin(User user) {
		
		LOGGER.debug("Adding last login...");
		
		repository.saveAndFlush(user);
	}
	
}
