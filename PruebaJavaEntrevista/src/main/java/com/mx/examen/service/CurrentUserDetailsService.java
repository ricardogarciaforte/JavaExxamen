package com.mx.examen.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mx.examen.entity.CurrentUser;
import com.mx.examen.entity.User;

/**
 * This Class abstracts the operations related to the Service layer for a User.
 * 
 * @author Ricardo Garcia Forte
 * @version 1.0
 * @created 2018-11-30
 */
@Service
public class CurrentUserDetailsService implements UserDetailsService, ApplicationListener<AuthenticationSuccessEvent> {

	private static final Logger LOGGER = Logger.getLogger(CurrentUserDetailsService.class);

	@Autowired
	private UserService userService;

	/**
	 * This method is responsible for fetching the username of the user in the database and storing it 
	 * in the information of the current user of the application.
	 */
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		LOGGER.debug("Authenticating user with email={" + email + "}");
		User user;		
		try {			
            user = this.userService.findUserByEmail(email);
        } catch (Exception ex) {
            LOGGER.error("Error in retrieving user");
            throw new UsernameNotFoundException("Was not found user {" + email + "}");
        }

		return new CurrentUser(user);
	}
	
	/**
	 * This method is responsible for managing the information of a user with access allowing the application.
	 */
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		
		LOGGER.debug("Registering Last Login...");		 
        String userName = ((UserDetails) event.getAuthentication().getPrincipal()).getUsername();        
        User user = userService.findUserByEmail(userName);
        user.setLastLogin(new Date());
        this.userService.registerLastLogin(user);
     }

}
