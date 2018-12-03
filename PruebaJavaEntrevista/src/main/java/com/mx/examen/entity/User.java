package com.mx.examen.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * This Class represents a USERS Database Entity.
 * 
 * @author Ricardo Garcia Forte *
 * @version 1.0
 * @created 2018-11-30
 */
@Entity
@Table(name = "USERS")
public class User implements Serializable {

	private static final long serialVersionUID = 7207211942352790856L;
	/**
	 * Represents the identifier attribute of the User.
	 */
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * Represents the attribute with the full name of the User.
	 */
	@Column(name = "full_name", nullable = true)
	private String fullName;
	/**
	 * Represents the e-mail attribute of the User.
	 */
	@Column(nullable = true, unique = true, updatable = false)
	private String email;	
	/**
	 * This is stored as a hash in the database to ensure greater security of the information.
	 */
	@Column(name = "password_hash", nullable = true)
	private String password;
	/**
	 * Represents the attribute with the user's user perfil.
	 */
	@Column(nullable = true) 
	@Enumerated(EnumType.STRING)
	private Perfil perfil;
	/**
	 * Stores the date and time of the last login performed by a user.
	 */
	@Column(name = "last_login") 
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;	
	/**
	 * This attribute is transient because it only serves to perform the password confirmation during 
	 * the user's registration.
	 */
	@Transient
	private String passwordRepeated;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getPasswordRepeated() {
		return passwordRepeated;
	}

	public void setPasswordRepeated(String passwordRepeated) {
		this.passwordRepeated = passwordRepeated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fullName=" + fullName + ", email=" + email + ", password=" + password
				+ ", perfil=" + perfil + ", lastLogin=" + lastLogin + ", passwordRepeated=" + passwordRepeated + "]";
	}

}
