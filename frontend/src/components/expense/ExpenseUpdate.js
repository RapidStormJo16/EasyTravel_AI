import React, { useState, useEffect } from 'react';
import Base from '../Base';
import axios from 'axios';
import { useCookies } from 'react-cookie';
import { useNavigate } from "react-router-dom";

import {toast} from 'react-toastify';
import './update.css'

const ExpenseUpdate = () => {
  const [expense, setExpense] = useState({});
  const [cookies] = useCookies(['selectedExpenseId', 'userId']);

  const navigate = useNavigate();
  
  useEffect(() => {
    const fetchExpense = async () => {
      try {
        const response = await axios.get(`http://localhost:8012/expenses/${cookies.selectedExpenseId}`);
        console.log(response.data);
        setExpense(response.data); // Set fetched expense data in state
      } catch (error) {
        console.error('Error fetching expense:', error);
      }
    };

    if (cookies.selectedExpenseId) {
      fetchExpense(); // Fetch expense details when component mounts
    }

    return () => {
      // Clean up: Clear the selectedExpenseId cookie when component unmounts
      // setCookie('selectedExpenseId', '', { path: '/' });   
    };
  }, [cookies.selectedExpenseId]); // Execute effect when selectedExpenseId changes

  const handleUpdateExpense = async (e) => {
    e.preventDefault();

    const updatedExpense = {
      expenseId: cookies.selectedExpenseId, // Get expenseId from cookie
      userId: cookies.userId, // Get userId from cookie
      tags: expense.tags,
      description: expense.description,
      amount: parseFloat(expense.amount)
    };
    console.log(updatedExpense);
    try {
      await axios.put('http://localhost:8012/expenses/update', updatedExpense);
      toast.success("Expense updated successfully");
      navigate('/expense');
      console.log('Expense updated successfully!');
      // Handle success - e.g., show a success message or redirect the user
    } catch (error) {
        toast.error("Error updating expense");
      console.error('Error updating expense:', error);
      // Handle error - e.g., show an error message to the user
    }

  };

  const handleInputChange = (e) => {
    e.preventDefault();
    setExpense({ ...expense, [e.target.id]: e.target.value });
  };

  return (
    <>
      <Base />
      <form className="form-vessel my-4 mx-5" onSubmit={handleUpdateExpense}>
        <div className="form-group">
          <label htmlFor="tags">Tags</label>
          <input
            type="text"
            className="form-control"
            id="tags"
            placeholder="Enter tag"
            value={expense.tags || ''}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="description">Description</label>
          <input
            type="text"
            className="form-control"
            id="description"
            placeholder="Enter description"
            value={expense.description || ''}
            onChange={handleInputChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="amount">Amount</label>
          <input
            type="number"
            className="form-control"
            id="amount"
            placeholder="Enter amount"
            value={expense.amount || ''}
            onChange={handleInputChange}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary update">
          Update
        </button>
      </form>
    </>
  );
};

export default ExpenseUpdate;
