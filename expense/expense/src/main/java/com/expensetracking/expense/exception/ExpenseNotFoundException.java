package com.expensetracking.expense.exception;

public class ExpenseNotFoundException extends RuntimeException {

	public ExpenseNotFoundException(String message) {
		super(message);
	}
}
