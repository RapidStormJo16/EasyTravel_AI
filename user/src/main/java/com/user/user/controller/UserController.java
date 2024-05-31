package com.user.user.controller;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.user.user.entity.User;
import com.user.user.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/registration")
	public ResponseEntity<String> createUser(@RequestBody User user) {
	    return Optional.ofNullable(user)
	        .map(service::createUser)
	        .map(createdUser -> ResponseEntity.status(HttpStatus.CREATED).body("Created!"))
	        .orElseGet(() -> new ResponseEntity<>("User is null", HttpStatus.BAD_REQUEST));
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) {
		
		
		try{User loggedUser = service.loginUser(email,password);
		return ResponseEntity.ok(loggedUser);}
		catch(RuntimeException e1) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: "+e1.getMessage());
		}
	}
	
	@GetMapping("/users")
	public List<User> allData() {
		return service.findAllUsers();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable long id) {
		try {
		String message = service.deleteUser(id);
		return ResponseEntity.ok(message);}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());}
	}
	
	@Bean
	public FilterRegistrationBean corsFilter() {
		
		UrlBasedCorsConfigurationSource source =  new UrlBasedCorsConfigurationSource();
		
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.addAllowedOriginPattern("*");
	    Stream.of("Authorization", "Content-Type", "Accept").forEach(corsConfiguration::addAllowedHeader);
	    Stream.of("POST", "GET", "PUT", "DELETE", "OPTIONS").forEach(corsConfiguration::addAllowedMethod);
		corsConfiguration.setMaxAge(3600L);
		
		source.registerCorsConfiguration("/**",corsConfiguration);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		
		return bean;
	}
	
}
