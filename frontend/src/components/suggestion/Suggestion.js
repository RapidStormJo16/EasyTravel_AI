import React, { useState } from 'react';
import Base from "../Base";
import Hero from '../Hero';
import sImg from "../../assets/8.jpg";
import Footer from '../Footer';
import SuggestionContent from './SuggestionContent';
import SuggestionForm from './SuggestionForm';

const Suggestion = () => {
    
    return (
        <Base>
            <Hero
            cName="hero-mid"
            heroImg={sImg}
            title="We Value Your Views"
            // text="Travel Planning Made Easier."
            btnClass="hide"
        />
            <SuggestionContent/>
            <SuggestionForm/>
            <Footer/>
        </Base>
    );
};

export default Suggestion;
