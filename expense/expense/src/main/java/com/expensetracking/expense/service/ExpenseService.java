package com.expensetracking.expense.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expensetracking.expense.entity.Expense;
import com.expensetracking.expense.exception.ExpenseNotFoundException;
import com.expensetracking.expense.exception.UserNotFoundException;
import com.expensetracking.expense.repository.ExpenseRepository;

@Service
public class ExpenseService {
	
	@Autowired
	private ExpenseRepository repository;
	

	public void logEntry(Expense expense) {
		repository.save(expense);
	}

	public double totalAmount(Long userId) {
		if(userId == null) {
			throw new IllegalArgumentException("User ID cannot be null.");
		}
	
		List<Expense> expenses = Optional.ofNullable(repository.findByUserId(userId))
				.orElseThrow(() -> new UserNotFoundException("User ID: "+userId+" does not exist"));

		return expenses.stream().mapToDouble(Expense::getAmount).sum();
	}
	
	public List<Expense> getAllExpensesForUser(Long userId){
		List<Expense> list = Optional.ofNullable(repository.findByUserId(userId))
				.filter(l -> !l.isEmpty())
				.orElseThrow(() -> new ExpenseNotFoundException("Expense for this user id not found"));
		return list;
	}
	
	public void deleteExpense(Long expenseId) {
		if(expenseId == null) {
			throw new IllegalArgumentException("Expense ID cannot be null, the expense doesnt exist");
		}
		
		if(!repository.existsById(expenseId)) {
			throw new ExpenseNotFoundException("Expense with Id: "+ expenseId+" does not exist.");
		}
		repository.deleteById(expenseId);
	}
	
	public void updateExpense(Expense expense) {
		
		if(!repository.existsById(expense.getExpenseId())) {
			throw new ExpenseNotFoundException("Expense with Id: "+ expense.getExpenseId()+" does not exist.");
		}
		
		Expense expense1 = repository.findById(expense.getExpenseId())
				.orElseThrow(() -> new ExpenseNotFoundException("Expense with Id: "+expense.getExpenseId()+" does not exist"));
		
		expense1.setTags(expense.getTags());
		expense1.setDescription(expense.getDescription());
		expense1.setAmount(expense.getAmount());
		
		repository.save(expense1);
	}
}
