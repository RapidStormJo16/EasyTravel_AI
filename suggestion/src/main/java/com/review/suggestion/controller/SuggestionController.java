package com.review.suggestion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.review.suggestion.entity.Suggestion;
import com.review.suggestion.service.SuggestionService;

@RestController
@RequestMapping("/suggestions")
@CrossOrigin("*")
public class SuggestionController {
	
	@Autowired
	private SuggestionService service;
	
	@PostMapping("/feedback")
	public ResponseEntity<?> submitSuggestion(@RequestBody Suggestion suggestion){
		try {
			if(suggestion!=null && suggestion.getContent()!=null) { 
			service.submitSuggestion(suggestion);
			return ResponseEntity.ok(suggestion);
			}else {
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Cannot be empty");
			}
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("an error occurred: "+e.getMessage());
		}
	}
	
	@GetMapping("/allSuggestions")
	public ResponseEntity<?> getAllSuggestions(){
		try{return ResponseEntity.ok(service.getAllSuggestions());}
		catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@Bean
	public FilterRegistrationBean corsFilter() {
		
		UrlBasedCorsConfigurationSource source =  new UrlBasedCorsConfigurationSource();
		
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.addAllowedOriginPattern("*");
		corsConfiguration.addAllowedHeader("Authorization");
		corsConfiguration.addAllowedHeader("Content-Type");
		corsConfiguration.addAllowedHeader("Accept");
		corsConfiguration.addAllowedMethod("POST");
		corsConfiguration.addAllowedMethod("GET");
		corsConfiguration.addAllowedMethod("PUT");
		corsConfiguration.addAllowedMethod("DELETE");
		corsConfiguration.addAllowedMethod("OPTIONS");
		corsConfiguration.setMaxAge(3600L);




		
		source.registerCorsConfiguration("/**",corsConfiguration);
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
		
		return bean;
	}
	

}
