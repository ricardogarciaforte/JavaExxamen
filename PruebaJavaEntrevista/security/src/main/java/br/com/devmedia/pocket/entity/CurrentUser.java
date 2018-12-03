package br.com.devmedia.pocket.entity;

import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 9140802002096542144L;
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
