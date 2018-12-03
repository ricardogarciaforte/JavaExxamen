package com.mx.examen.entity;

import org.springframework.security.core.authority.AuthorityUtils;

/**
 * This Class represents to Spring Security the information of the user who 
 * is currently logged into the system, so that it can manage the information 
 * of the logged in user.
 * 
 * @author Ricardo Garcia Forte
 * @version 1.0
 * @created 2018-11-30
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 5726290603969144522L;
	/**
	 * Represents the user who is logged in to the system.
	 */
	private User user;

    public CurrentUser(User user) {    	
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getPerfil().toString()));
        this.user = user;
    }
    
    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public Perfil getPerfil() {
        return user.getPerfil();
    }

}
