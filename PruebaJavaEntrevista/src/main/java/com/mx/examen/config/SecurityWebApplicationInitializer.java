package com.mx.examen.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * This Class is responsible for initializing Spring Security
 * 
 * @author Ricardo Garcia Forte
 * @version 1.0
 * @created 2018-11-30
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

	/**
	 * This method is responsible for informing Spring, 
	 * where is the Spring Security configuration class.
	 */
	public SecurityWebApplicationInitializer() {
		
        super(SecurityConfig.class);
    }

}
