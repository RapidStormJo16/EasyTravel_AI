import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { Button } from "reactstrap";
import styled from 'styled-components';
import Base from "../Base";
import Footer from "../Footer";
import Hero from "../Hero";
import Services from "../Services";
import Content from "./Content";


const Home=()=>{
    
    return(
        <>
        <Base/>
        
        <Hero
        cName="hero"
        heroImg="https://images.unsplash.com/photo-1488085061387-422e29b40080?q=80&w=1931&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        title="EASYTRAVEL.AI"
        text="Travel Planning Made Easier."
        url="#redirect"
        btnClass="show"
        buttonText="Get Started"
        />
        <Content/>
        <Services/>
        <Footer/>
        </>
        


        

        );
}

export default Home;





{/* <Button color="danger" onClick={logOut}>Logout</Button> */}
// const navigate=useNavigate();
// useEffect(()=>{
//     if(!localStorage.getItem('user')){
//         navigate('/login');
//     }
// },[navigate]);

// function logOut()
// {
//     localStorage.clear();
//     navigate('/login');
// }