import { useState } from "react";
import axios from 'axios';
import { toast } from 'react-toastify';
import './login.css';
import backImg from '../../assets/2.jpg';
import logoImg from '../../assets/signup2.jpeg'; // Update with your logo image path
import { useNavigate } from "react-router-dom";

const Signup = () => {
    const [data, setData] = useState({
        name: '',
        email: '',
        password: ''
    });


    const handleChange = (event, property) => {
        setData({ ...data, [property]: event.target.value });
    }

    const resetData = () => {
        setData({
            name: '',
            email: '',
            password: '',
        });
    }

    const navigate=useNavigate();
  

    const submitForm = (event) => {
        event.preventDefault();


        // if (error.isError) {
        //     toast.error("Form data is invalid, correct all details");
        //     return;
        // }

        axios.post('http://localhost:8013/user/registration', {
            username: data.name,
            email: data.email,
            password: data.password
        })
        .then((response) => {
            console.log(response.data);
            console.log("success log");
            toast.success("User is registered successfully");
            navigate("/login");
            resetData();
        })
        .catch((error) => {
            console.log(error);
            console.log(error.response.data);
            toast.error(error.response.data);
        });
    }
  
  return (
    <section className="Form my-4 mx-5">
      <div className="container">
        <div className="row no-gutters">
          <div className="col-lg-5">
            <img src={logoImg} class="img-fluid signup" alt="logoImg" />
          </div>
          <div className="col-lg-7 px-5 pt-5">
            <h1 class="font-weight-bold py-3">Sign In</h1>
            <h4>Welcome! Sign up now!</h4>
            <form onSubmit={submitForm}>
              <div className="form-row">
                <div className="col-lg-7">
                  <input type="text" placeholder="Enter Your Name:" class="form-control my-3 p-3"
                  onChange={(e) => handleChange(e, 'name')} value={data.name} required={true}/>
                </div>
              </div>
              <div className="form-row">
                <div className="col-lg-7">
                  <input type="email" placeholder="Enter Email Address:" class="form-control my-3 p-3"
                  onChange={(e) => handleChange(e, 'email')} value={data.email} required={true}/>
                </div>
              </div>
              <div className="form-row">
                <div className="col-lg-7">
                  <input type="password" placeholder="Enter Password:" class="form-control my-3 p-3"
                  onChange={(e) => handleChange(e,'password')}value={data.password} required={true}/>
                </div>
              </div>
              <div className="form-row">
                <div className="col-lg-7">
                  <button type="submit" class="btn1 mt-3 mb-5">Submit</button>
                </div>
              </div>
              <p>Already registered? <a href="/login"> Log In Now!</a></p>

            </form>
          </div>
        </div>
      </div>
    </section>
  );
};

export default Signup;
