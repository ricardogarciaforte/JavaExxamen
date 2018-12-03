package br.com.devmedia.pocket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devmedia.pocket.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public User findByEmail(String email);
}
