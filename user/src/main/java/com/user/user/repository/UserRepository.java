package com.user.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmailAndPassword(String email, String password);
	User findByEmail(String email);
	User findByUsername(String username);

}
