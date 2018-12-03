package com.mx.examen.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mx.examen.service.CurrentUserDetailsService;

/**
 * This Class represents the configuration of Spring Security.
 * 
 * @author Ricardo Garcia Forte
 * @version 1.0
 * @created 2018-11-30
 */
@Configuration
@EnableWebSecurity
@ComponentScan("com.mx.examen")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CurrentUserDetailsService userDetailsService;
	
	/**
	 * This method is responsible for configuring the security features of the application.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//Defines the URL's that each user profile of the application can access.
		http.authorizeRequests()
                    .antMatchers("/css/**").permitAll()
	                .antMatchers("/", "/home", "/auth/**").permitAll()
	                .antMatchers("/user/{id}").hasAnyAuthority("ADMIN") //indicates that only user with admin profile
	                .antMatchers("/user/**").hasAuthority("ADMIN")
	                .anyRequest().authenticated()
	            .and() //Manages the operations performed on the system login form
                    .formLogin()
                    .loginPage("/auth/login")
                    .failureUrl("/auth/login?error=true")
                    .usernameParameter("j_username")
                    .passwordParameter("j_password")
                    .permitAll()
	            .and()
		            .logout()
                    .logoutSuccessUrl("/auth/login?logout=true")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
	            .and()
	            	.exceptionHandling().accessDeniedPage("/auth/denied");

	}
	
	/**
	 * This method is responsible for encrypting and validating in the database 
	 * whether the user's password is correct.
	 */
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        
		auth.userDetailsService(this.userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }	

}
