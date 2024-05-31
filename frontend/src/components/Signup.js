import { useState } from "react";
import axios from 'axios';
import { toast } from 'react-toastify';

const Signup = () => {

    const [data, setData] = useState({
        name: '',
        email: '',
        password: ''
    });

    const [error, setError] = useState({
        errors: {},
        isError: false
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

    const submitForm = (event) => {
        event.preventDefault();

        if (error.isError) {
            toast.error("Form data is invalid, correct all details");
            return;
        }

        axios.post('http://localhost:8013/user/registration', {
            username: data.name,
            email: data.email,
            password: data.password
        })
        .then((response) => {
            console.log(response);
            console.log("success log");
            toast.success("User is registered successfully");
            resetData();
        })
        .catch((error) => {
            console.log(error);
            console.log("Error log");
            setError({
                errors: error,
                isError: true
            });
        });
    }

    return (
        <div className="container">
            <div className="row mt-4">
                <div className="col-sm-6 col-sm-offset-3 mx-auto">
                    <div className="card">
                        <div className="card-header">
                            <h4>Fill the following information to sign up!</h4>
                        </div>
                        <div className="card-body">
                            <form onSubmit={submitForm}>
                                <div className="form-group">
                                    <label htmlFor="name" className="form-label">Name:</label>
                                    <input type="text" className="form-control" placeholder="Enter your name" id="name"
                                        onChange={(e) => handleChange(e, 'name')} value={data.name} required={true}/>

                                </div>

                                <div className="form-group">
                                    <label htmlFor="email" className="form-label">Enter Email Id:</label>
                                    <input type="email" className="form-control" placeholder="Email Id" id="email"
                                        onChange={(e) => handleChange(e, 'email')} value={data.email} required={true} />
                                </div>

                                <div className="form-group">
                                    <label htmlFor="password">Enter Password:</label>
                                    <input type="password" placeholder="Password" id="password" 
                                    onChange={(e) => handleChange(e, 'password')} value={data.password} required={true} />
                                </div>

                                <div className="container">
                                    <button type="submit" className="btn btn-dark btn-block" >Register</button>
                                    <button type="reset" className="btn btn-secondary" onClick={resetData} >Reset</button>
                                </div>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default Signup;