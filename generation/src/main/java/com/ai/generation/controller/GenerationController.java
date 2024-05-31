package com.ai.generation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.ai.generation.exception.GenerationException;
import com.ai.generation.service.GenerationService;

@RestController
@RequestMapping("/generation")
@CrossOrigin("*")
public class GenerationController {

	@Autowired
	GenerationService genService;
	
	@GetMapping("/itinerary")
	public ResponseEntity<String>getItinerary(@RequestParam String destination, @RequestParam int noOfDays, @RequestParam int budget, @RequestParam String companions, @RequestParam String activities, @RequestParam String cuisine) {
		try{
			return ResponseEntity.ok().body(genService.getItinerary(destination, noOfDays, budget, companions, activities, cuisine));}
		catch(GenerationException e) {
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
