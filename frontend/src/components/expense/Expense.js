import Base from "../Base"

import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ExpenseForm from './ExpenseForm';
import ExpenseList from './ExpenseList';
import TotalAmount from './TotalAmount';
import Hero from "../Hero";
import aboutImg from "../../assets/9.jpg";
import Footer from "../Footer";

const Expense = () => {
  const [expenses, setExpenses] = useState([]);

  // Fetch all expenses for a user
  const fetchExpenses = async (userId) => {
    try {
      const response = await axios.get(`http://localhost:8012/expenses/user/${userId}`);
    //   console.log(response.data);
      setExpenses(response.data);
    } catch (error) {
      console.error('Error fetching expenses:', error);
    }
  };

  useEffect(() => {
    const userId = localStorage.getItem('user');
    fetchExpenses(userId);
  }, []);

  return (
    <Base>
     <div>
     <Hero
        cName="hero-mid"
        heroImg={aboutImg}
        title="Expense Tracker"
        // text="Travel Planning Made Easier."
        btnClass="hide"
        />
      <TotalAmount expenses={expenses} />
      <ExpenseForm fetchExpenses={fetchExpenses} />
      <ExpenseList expenses={expenses} fetchExpenses={fetchExpenses} />
      <Footer/>
    </div>
    </Base>
   
  );
};

export default Expense;

