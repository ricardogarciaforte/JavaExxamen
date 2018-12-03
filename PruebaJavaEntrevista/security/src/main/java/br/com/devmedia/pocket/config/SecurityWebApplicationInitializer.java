package br.com.devmedia.pocket.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Substitui o filtro SpringSecurityFilterChain adicionado ao arquivo web.xml
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityWebApplicationInitializer() {
        super(SecurityConfig.class);
    }
}