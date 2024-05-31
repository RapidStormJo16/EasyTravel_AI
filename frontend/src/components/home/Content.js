import React from 'react'
import pImg1 from '../../assets/11.jpg';
import pImg2 from '../../assets/10.jpg';
import pImg3 from '../../assets/14.jpg';
import pImg4 from '../../assets/17.jpg';
import '../content.css';
import ContentData from '../ContentData';
export default function Content() {
  return (
    <div class="project">
        <h1>About The Project</h1>
        <p>A Brief Insight Into This Project.</p>
        
        <ContentData
        className="first-desc"
        heading="Purpose Of This Project"
        text="The system aims to assist the users in planning their itineraries by using artificial intelligence
               technologies. Users can track their expenses and specify their travel preferences, including destinations, dates, duration, 
               budget, eating preferences, number of people and their interest in activities in that destination. 
               EasyTravel.AI will generate a personalized travel itinerary tailored to all the user's 
               preferences and enhance the travel experience."
        img1={pImg1}
        img2={pImg2}
        />

        <ContentData
        className="first-desc-reverse"
        heading="Motivation Behind"
        text="EasyTravel.AI is a platform designed to simplify the process and reduce the time of expense tracking, travel 
        planning and decision making. It serves as an assistant connecting travelers to relevant information, regarding 
        stay, places to visit like heritage sites, beaches, music festivals, shopping, nightlife,  good restaurants according 
        to the preferences, cost of travel and accommodation and various activities occurring in those places. They can explore 
        their choice of destination using the same and even customize their plans based on their preferences."
        img1={pImg3}
        img2={pImg4}
        />
    </div>
  )
}
