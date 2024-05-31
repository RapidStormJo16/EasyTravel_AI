package com.user.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.user.user.entity.User;
import com.user.user.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
public User createUser(User user) {
		
		Optional.ofNullable(user.getUsername()).filter(u->!u.isEmpty()).orElseThrow(()-> new RuntimeException("Invalid username"));
		Optional.ofNullable(user.getEmail()).filter(u->!u.isEmpty()).orElseThrow(()-> new RuntimeException("Invalid email."));
		Optional.ofNullable(user.getPassword()).filter(u->!u.isEmpty()).orElseThrow(()->new RuntimeException("Invalid password."));
		Optional.ofNullable(repository.findByEmail(user.getEmail())).ifPresent(u-> { throw new RuntimeException("Already registered with same email");});
		
		return repository.save(user);
	}

	
public User loginUser(String email, String password) {
	
	return Optional.ofNullable(repository.findByEmailAndPassword(email, password)).orElseThrow(()-> new RuntimeException("Account does not exist or incorrect credentials"));
	}
	
	public User findById(Long id) {
		User user = repository.findById(id).orElse(null);
		return user;
	}
	
	public List<User> findAllUsers(){
		return Optional.ofNullable(repository.findAll()).orElseThrow(() -> new RuntimeException("Error retrieving all users."));

	}
	
	public String deleteUser(long id) {
		if(repository.existsById(id)) {
		repository.deleteById(id);
		return "Your account with id:"+id+" is deleted successfully";}
		else {			
			throw new IllegalArgumentException("User not found");
		}

	}
}
