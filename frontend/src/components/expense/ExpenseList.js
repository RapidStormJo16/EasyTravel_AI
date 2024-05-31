import React from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import { useCookies } from 'react-cookie';
import { useEffect } from 'react';
import {toast} from 'react-toastify';
import "./list.css"

const ExpenseList = ({ expenses, fetchExpenses }) => {
  const navigator = useNavigate();
  const [cookies, setCookie] = useCookies(['selectedExpenseId', 'userId']);

  const handleDelete = async (expenseId) => {
    try {
      await axios.delete(`http://localhost:8012/expenses/delete/${expenseId}`);
      fetchExpenses(cookies.userId); // Refresh expenses after deletion
      toast.success("Expense deleted successfully");
    } catch (error) {
      toast.error("Error deleting expense")
      console.error('Error deleting expense:', error);
    }
  };

  const handleUpdate = (expenseId) => {
    // Store selected expenseId in a cookie
    setCookie('selectedExpenseId', expenseId, { path: '/' });
    setCookie('userId',localStorage.getItem('user'));
    navigator(`/update/${expenseId}`);
  };

  return (
    <div className="expense-list">
      <h2>Expenses</h2>
      <ul>
        {expenses.map((expense) => (
          <li key={expense.expenseId}>
            {expense.tags} - {expense.description} - Rs. {expense.amount}
            <button onClick={() => handleDelete(expense.expenseId)}>Delete</button>
            <button onClick={() => handleUpdate(expense.expenseId)}>Update</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default ExpenseList;