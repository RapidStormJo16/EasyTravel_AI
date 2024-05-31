import React from 'react';
import './services.css';
import ServicesData from './ServicesData';
import genImg from '../assets/AI.jpg';
import exImg from '../assets/Expense.jpg'

export default function Services() {
  return (
    <div>
        <div className="services">
            <h1>Services Provided</h1>
            <p>We providing cutting edge solutions to cut the travel lazy.</p>
            <div id="redirect" className="service-card">
                <ServicesData
                image={genImg}
                heading="Personalized Itinerary"
                text="Using the famous GPT model, this service will provide you with a 
                tailor made itinerary for your trip by considering all your preferences
                such as place, budget, activities, company and even cuisine. Here is our
                AI-Powered Personal Travel Agent."
                link="/itinerary"
                />
                <ServicesData
                image={exImg}
                heading="Expense Tracking"
                text="During trips, people don't realise how much they spend
                over food, travel, parties, drinks, events, festival and importantly
                shopping. Why look anywhere else when all of this can be handled with
                our platform. Here is the Expense Tracking Service."
                link="/expense"
                />

            </div>
        </div>
    </div>
  )
}
