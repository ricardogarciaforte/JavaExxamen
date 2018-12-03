package com.mx.examen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.examen.entity.User;

/**
 * Interface that contains the user operations of the application data layer.
 * 
 * @author Ricardo Garcia Forte
 * @version 1.0
 * @created 2018-11-30
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 * Search for a user in the database by Email.
	 */
	public User findByEmail(String email);

}
