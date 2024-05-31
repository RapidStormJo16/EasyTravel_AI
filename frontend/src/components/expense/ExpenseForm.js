import React, { useState } from 'react';
import axios from 'axios';
import { toast } from 'react-toastify';
import './content.css';
import { useCookies } from 'react-cookie';



const ExpenseForm = ({ fetchExpenses }) => {
    let uid  = localStorage.getItem("user");
    // console.log(uid);
  const [formData, setFormData] = useState({ tags: '',userId: uid, description: '', amount: '' });

  const handleInputChange = (e) => {
    e.preventDefault();
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if(formData.tags===''||formData.description===''||formData.amount==='')
    {
        toast.error("Fields incomplete!")
    }
    else{
        try {
            console.log(formData);
          await axios.post('http://localhost:8012/expenses/log', formData);
          console.log(uid);
          fetchExpenses(uid); // Refresh expenses after adding
          toast.success("Expense Added Successfully!");
          setFormData({ tags: '',userId:uid, description: '', amount: '' });
        } catch (error) {
          console.log(uid);
          console.error('Error adding expense:', error);
        }

    }
    
  };

  return (
    <div className='expense-form'>
      {/* <h2>Add Expense</h2> */}
      <form onSubmit={handleSubmit}>
        <label>Enter Tag:</label>
        <input type="text" name="tags" value={formData.tags} onChange={handleInputChange} placeholder="Tags; Example:Food,Travel,Shopping,etc" />
        <label>Enter Description:</label>
        <input type="text" name="description" value={formData.description} onChange={handleInputChange} placeholder="Description Of Expenditure" />
        <label>Enter Amount:</label>
        <input type="number" name="amount" value={formData.amount} onChange={handleInputChange} placeholder="Amount Spent" />
        <button type="submit">Add Expense</button>
      </form>
    </div>
  );
};

export default ExpenseForm;
