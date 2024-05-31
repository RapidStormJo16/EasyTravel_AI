package com.expensetracking.expense.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expensetracking.expense.entity.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	List<Expense> findByUserId(Long userId);
}
