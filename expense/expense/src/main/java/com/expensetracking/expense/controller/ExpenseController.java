package com.expensetracking.expense.controller;

import java.util.Collections;
import java.util.List;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.expensetracking.expense.entity.Expense;
import com.expensetracking.expense.exception.ExpenseNotFoundException;
import com.expensetracking.expense.exception.UserNotFoundException;
import com.expensetracking.expense.service.ExpenseService;

@RestController
@RequestMapping("/expenses")
@CrossOrigin("*")

public class ExpenseController {
	
	@Autowired
	private ExpenseService service;
	
	//Logging the expense in the database
	@PostMapping("/log")
	public ResponseEntity<String> logExpense(@RequestBody Expense expense){
		service.logEntry(expense);
		
		return ResponseEntity.ok("Expense logged in successfully");
	}
	
	//Getting all expenses from user
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getAllExpensesForUser(@PathVariable long userId){
		try{List<Expense> expenses = service.getAllExpensesForUser(userId);
		return ResponseEntity.ok(expenses);}
		catch(ExpenseNotFoundException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	//Getting total amount
	@GetMapping("/user/total/{userId}")
	public ResponseEntity<Double> totalAmount(@PathVariable Long userId){
		double amount = service.totalAmount(userId);
		return ResponseEntity.ok(amount);
	}
	
	//Deleting Expenses
	@DeleteMapping("/delete/{expenseId}")
	public ResponseEntity<String> deleteExpense(@PathVariable long expenseId){
		try {
		service.deleteExpense(expenseId);
		return ResponseEntity.ok("Expense is deleted");}
		catch(IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		catch(ExpenseNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	//Update Expenses
	@PutMapping("/update")
	public ResponseEntity<String> updateExpense(@RequestBody Expense expense){
		try{service.updateExpense(expense);
		return ResponseEntity.ok("Expense Updated");}
		catch(ExpenseNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
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
