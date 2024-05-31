import { Button } from 'bootstrap';
import React from 'react';
import { BrowserRouter as Router, Route, Routes} from 'react-router-dom';
import './App.css';

import Home from './components/home/Home';
import Signup from './components/login/Signup';
import Login from './components/login/Login';
import Itinerary from './components/itinerary/Itinerary';
import Expense from './components/expense/Expense';
import Suggestion from './components/suggestion/Suggestion';

import { ToastContainer} from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import ExpenseUpdate from './components/expense/ExpenseUpdate';

function App() {
  return (

      <div className="App">
        <Router>
          <ToastContainer position='bottom-center'/>
          <Routes>
            <Route path="/" element={<Home/>} />
            <Route path="/login" element={<Login/>} />
            <Route path="/signup" element={<Signup/>} />
            {/* <Route path="/about" element={<About/>} /> */}
            <Route path="/itinerary" element={<Itinerary/>} />
            <Route path="/expense" element={<Expense/>} />
            <Route path="/update/:expenseId" element={<ExpenseUpdate/>} />
            <Route path="/suggestion" element={<Suggestion/>} />

          </Routes>
        </Router>

      </div>
      
  );
}

export default App;