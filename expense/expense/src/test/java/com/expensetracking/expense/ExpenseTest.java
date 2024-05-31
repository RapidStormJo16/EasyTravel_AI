package com.expensetracking.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.expensetracking.expense.entity.Expense;

public class ExpenseTest {

    @Test
    public void testExpenseConstructor() {
        long expenseId = 1L;
        long userId = 1L;
        String tags = "Food";
        String description = "Lunch";
        double amount = 20.0;

        Expense expense = new Expense(expenseId, userId, tags, description, amount);

        assertEquals(expenseId, expense.getExpenseId());
        assertEquals(userId, expense.getUserId());
        assertEquals(tags, expense.getTags());
        assertEquals(description, expense.getDescription());
        assertEquals(amount, expense.getAmount());
    }
    
    @Test
    public void testGettersAndSetters() {
    	Expense expense = new Expense();
    	expense.setExpenseId(1L);
    	expense.setUserId(1L);
    	expense.setTags("Food");
    	expense.setDescription("Lunch");
    	expense.setAmount(20.0);
    	
    	assertEquals(1L,expense.getExpenseId());
    	assertEquals(1L,expense.getUserId());
    	assertEquals("Food",expense.getTags());
    	assertEquals("Lunch",expense.getDescription());
    	assertEquals(20.0,expense.getAmount());
    }
}
