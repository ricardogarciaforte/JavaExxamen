package br.com.devmedia.pocket.service;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.devmedia.pocket.entity.CurrentUser;
import br.com.devmedia.pocket.entity.User;

@Service
public class CurrentUserDetailsService implements UserDetailsService, ApplicationListener<AuthenticationSuccessEvent> {

	private static final Logger LOGGER = Logger.getLogger(CurrentUserDetailsService.class);

	@Autowired
	private UserService userService;

	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		LOGGER.debug("Authenticating user with email={" + email + "}");

		User user;		
		try {			
            user = userService.findUserByEmail(email);
        } catch (Exception ex) {
            LOGGER.error("Error in retrieving user");
            throw new UsernameNotFoundException("Was not found user {" + email + "}");
        }

		return new CurrentUser(user);
	}
	
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		LOGGER.debug("Registering Last Login...");
		 
        String userName = ((UserDetails) event.getAuthentication().getPrincipal()).getUsername();
        
        User user = userService.findUserByEmail(userName);
        user.setLastLogin(new Date());
        userService.registerLastLogin(user);
     }
}
