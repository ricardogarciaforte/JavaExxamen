
package com.mx.examen.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mx.examen.entity.User;
import com.mx.examen.repository.UserRepository;

/**
 * This Class abstracts the operations related to the Service layer for a User.
 * 
 * @author Ricardo Garcia Forte
 * @version 1.0
 * @created 2018-11-30
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserService {
	
	private static final Logger LOGGER = Logger.getLogger(UserService.class);

	@Autowired
	private UserRepository repository;

	/**
	 * Search for a User in the Database by email.
	 */
    public User findUserByEmail(String email) {
		
		LOGGER.debug("Finding user by email{" + email + "}");
		
		return this.repository.findByEmail(email);
	}

    /**
	 * Search for a User in the Database by identifier.
	 */
	public User findById(Long id) {

		LOGGER.debug("Finding user id{" + id + "}");

		return this.repository.findOne(id);
	}

	/**
	 * Search all users in the Database..
	 */
	public List<User> findUsers() {
		
		LOGGER.debug("Get All Users");
		
		return this.repository.findAll();
	}

	/**
	 * create a new user by encrypting your password.
	 */
	public void createUser(User user) {
		
		LOGGER.debug("Saving user{" + user.getEmail() + "}");
		//converts the user's password into a hash type
		String passwordHash = new BCryptPasswordEncoder().encode(user.getPassword());		
		user.setPassword(passwordHash);
		
		this.repository.save(user);		
	}

	/**
	 * registers the user who logs into the system.
	 */
	public void registerLastLogin(User user) {
		
		LOGGER.debug("Adding last login...");
		
		this.repository.saveAndFlush(user);
	}

}
