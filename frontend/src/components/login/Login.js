import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import './login.css';
import backImg from '../../assets/2.jpg';
import logoImg from '../../assets/login1.jpg'; // Update with your logo image path
import { toast } from 'react-toastify';

const Login = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post("http://localhost:8013/user/login", null, {
        params: {
          email,
          password,
        },
      });

      console.log("Login successful:", response.data);
      toast.success("Logged in successfully!")
      localStorage.setItem("user", response.data.id);
      navigate("/");

    } catch (error) {
      console.error("Login failed:", error.message);
      toast.error(error.response.data);
      setError("Invalid credentials. Please try again.");
    }
  };
  
  return (
    <section className="Form my-4 mx-5">
      <div className="container">
        <div className="row no-gutters">
          <div className="col-lg-5">
            <img src={logoImg} class="img-fluid login" alt="logoImg" />
          </div>
          <div className="col-lg-7 px-5 pt-5">
            <h1 class="font-weight-bold py-3">Login</h1>
            <h4>Welcome Back! Sign into your account</h4>
            <form onSubmit={handleLogin}>
              <div className="form-row">
                <div className="col-lg-7">
                  <input type="email" placeholder="Enter Email Address:" class="form-control my-3 p-3"
                  onChange={(e) => setEmail(e.target.value)} required/>
                </div>
              </div>

              <div className="form-row">
                <div className="col-lg-7">
                  <input type="password" placeholder="Enter Password:" class="form-control my-3 p-3"
                  onChange={(e) => setPassword(e.target.value)} required/>
                </div>
              </div>
              <div className="form-row">
                <div className="col-lg-7">
                  <button type="submit" class="btn1 mt-3 mb-5">Login</button>
                </div>
              </div>
              <p>Not registered yet? <a href="/signup"> Sign Up Now!</a></p>

            </form>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Login;
