package com.expensetracking.expense;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.expensetracking.expense.controller.ExpenseController;
import com.expensetracking.expense.entity.Expense;
import com.expensetracking.expense.exception.ExpenseNotFoundException;
import com.expensetracking.expense.service.ExpenseService;

public class ExpenseControllerTest {

    @Mock
    private ExpenseService service;

    @InjectMocks
    private ExpenseController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLogExpense() {
        // Given
        Expense expense = new Expense();
        
        // When
        ResponseEntity<String> response = controller.logExpense(expense);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Expense logged in successfully", response.getBody());
        verify(service, times(1)).logEntry(expense);
    }

    @Test
    public void testGetAllExpensesForUser() {
        // Given
        long userId = 1L;
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(1L, userId, "Food", "Lunch", 20.0));

        when(service.getAllExpensesForUser(userId)).thenReturn(expenses);

        // When
        ResponseEntity<?> response = controller.getAllExpensesForUser(userId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expenses, response.getBody());
    }

    @Test
    public void testGetAllExpensesForUser_WithExpenseNotFoundException() {
        // Given
        long userId = 2L;
        when(service.getAllExpensesForUser(userId)).thenThrow(new ExpenseNotFoundException("Expense not found"));

        // When
        ResponseEntity<?> response = controller.getAllExpensesForUser(userId);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Expense not found", response.getBody());
    }

    @Test
    public void testTotalAmount() {
        // Given
        long userId = 1L;
        double totalAmount = 100.0;

        when(service.totalAmount(userId)).thenReturn(totalAmount);

        // When
        ResponseEntity<Double> response = controller.totalAmount(userId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(totalAmount, response.getBody());
    }

    @Test
    public void testDeleteExpense() {
        // Given
        long expenseId = 1L;

        // When
        ResponseEntity<String> response = controller.deleteExpense(expenseId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Expense is deleted", response.getBody());
        verify(service, times(1)).deleteExpense(expenseId);
    }

    @Test
    public void testDeleteExpense_WithIllegalArgumentException() {
        // Given
        long expenseId = 2L;
        doThrow(new IllegalArgumentException("Invalid expense ID")).when(service).deleteExpense(expenseId);

        // When
        ResponseEntity<String> response = controller.deleteExpense(expenseId);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid expense ID", response.getBody());
    }

    @Test
    public void testUpdateExpense() {
        // Given
        Expense expense = new Expense(1L, 1L, "Food", "Lunch", 20.0);

        // When
        ResponseEntity<String> response = controller.updateExpense(expense);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Expense Updated", response.getBody());
        verify(service, times(1)).updateExpense(expense);
    }

    @Test
    public void testUpdateExpense_WithExpenseNotFoundException() {
        // Given
        Expense expense = new Expense(2L, 1L, "Food", "Lunch", 20.0);
        doThrow(new ExpenseNotFoundException("Expense not found")).when(service).updateExpense(expense);

        // When
        ResponseEntity<String> response = controller.updateExpense(expense);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Expense not found", response.getBody());
    }
}
