package com.expensetracking.expense;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.expensetracking.expense.entity.Expense;
import com.expensetracking.expense.exception.ExpenseNotFoundException;
import com.expensetracking.expense.exception.UserNotFoundException;
import com.expensetracking.expense.repository.ExpenseRepository;
import com.expensetracking.expense.service.ExpenseService;

public class ExpenseServiceTest {

    @Mock
    private ExpenseRepository repository;

    @InjectMocks
    private ExpenseService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLogEntry() {
        // Given
        Expense expense = new Expense();
        
        // When
        service.logEntry(expense);

        // Then
        verify(repository, times(1)).save(expense);
    }

    @Test
    public void testTotalAmount() {
        // Given
        Long userId = 1L;
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(1L, userId, "Food", "Lunch", 20.0));

        when(repository.findByUserId(userId)).thenReturn(expenses);

        // When
        double totalExpense = service.totalAmount(userId);

        // Then
        assertEquals(20.0, totalExpense);
    }

    @Test
    public void testTotalAmount_WithNullUserId() {
        // When-Then
        assertThrows(IllegalArgumentException.class, () -> {
            service.totalAmount(null);
        });
    }

    @Test
    public void testGetAllExpensesForUser() {
        // Given
        Long userId = 1L;
        List<Expense> expenses = new ArrayList<>();
        expenses.add(new Expense(1L, userId, "Food", "Lunch", 20.0));

        when(repository.findByUserId(userId)).thenReturn(expenses);

        // When
        List<Expense> result = service.getAllExpensesForUser(userId);

        // Then
        assertEquals(expenses, result);
    }

    @Test
    public void testGetAllExpensesForUser_WithEmptyList() {
        // Given
        Long userId = 2L;
        when(repository.findByUserId(userId)).thenReturn(new ArrayList<>());

        // When-Then
        assertThrows(ExpenseNotFoundException.class, () -> {
            service.getAllExpensesForUser(userId);
        });
    }

    @Test
    public void testDeleteExpense() {
        // Given
        Long expenseId = 1L;
        when(repository.existsById(expenseId)).thenReturn(true);

        // When
        service.deleteExpense(expenseId);

        // Then
        verify(repository, times(1)).deleteById(expenseId);
    }

    @Test
    public void testDeleteExpense_WithNonExistingId() {
        // Given
        Long expenseId = 2L;
        when(repository.existsById(expenseId)).thenReturn(false);

        // When-Then
        assertThrows(ExpenseNotFoundException.class, () -> {
            service.deleteExpense(expenseId);
        });
    }

    @Test
    public void testUpdateExpense() {
        // Given
        Expense expenseToUpdate = new Expense(1L, 1L, "Food", "Lunch", 20.0);
        Optional<Expense> optionalExpense = Optional.of(expenseToUpdate);

        when(repository.existsById(expenseToUpdate.getExpenseId())).thenReturn(true);
        when(repository.findById(expenseToUpdate.getExpenseId())).thenReturn(optionalExpense);

        // When
        service.updateExpense(expenseToUpdate);

        // Then
        verify(repository, times(1)).save(expenseToUpdate);
    }

    @Test
    public void testUpdateExpense_WithNonExistingId() {
        // Given
        Expense expenseToUpdate = new Expense(2L, 1L, "Food", "Lunch", 20.0);
        when(repository.existsById(expenseToUpdate.getExpenseId())).thenReturn(false);

        // When-Then
        assertThrows(ExpenseNotFoundException.class, () -> {
            service.updateExpense(expenseToUpdate);
        });
    }
}

