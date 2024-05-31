package com.expensetracking.expense.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="expense")
public class Expense {
	
	@Id
	@GeneratedValue
	private long expenseId;
	private long userId;
	private String tags;
	private String description;
	private double amount;
	
	

}
