package br.com.devmedia.pocket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.devmedia.pocket.service.CurrentUserDetailsService;

@Configuration
@EnableWebSecurity
@ComponentScan("br.com.devmedia.pocket")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CurrentUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
                    .antMatchers("/css/**").permitAll()
	                .antMatchers("/", "/home", "/auth/**").permitAll()
	                .antMatchers("/user/{id}").hasAnyAuthority("ADMIN") //.hasAnyAuthority("ADMIN", "USER")
	                .antMatchers("/user/**").hasAuthority("ADMIN")
	                .anyRequest().authenticated()
	            .and()
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
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }	
}
