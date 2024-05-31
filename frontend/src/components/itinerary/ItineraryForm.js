import axios from 'axios';
import React, { useState, useEffect } from 'react'
import './form.css';
export default function ItineraryForm() {
    const [destination, setDestination] = useState('');
    const [days, setDays] = useState('');
    const [budget, setBudget] = useState('');
    const [companions,setCompanions]  = useState('');

    const [activities, setActivities] = useState({
        sightseeing: false,
        beach: false,
        waterActivities: false,
        clubbing: false,
        adventureActivities: false,
        festivalsEvents: false,
        shopping: false,
        nightLife: false,
      });

    const [cuisine, setCuisine] = useState('');
    const [ithtml, setIthtml] = useState('');
    const [responseData, setResponseData]=useState(null);
    const [itinerary, setItinerary] = useState(null);


    const handleSubmit = async(e) => {
        e.preventDefault();

        const selectedActivities = Object.keys(activities).filter((key)=>activities[key]);
        const selectedActivitiesString = selectedActivities.join(',');
        // let response='';

        try{
          const response = await axios.get('http://localhost:8009/generation/itinerary', {params:{
            "destination": destination,
            "noOfDays": days,
            "budget": budget,
            "companions": companions,
            "activities": selectedActivitiesString,
            "cuisine": cuisine
        }});
        setIthtml(response.data);

        // console.log(response.data);
        // setResponseData(response.data);
        // console.log(responseData);
        // const formattedData = parseItinerary(responseData);
        // setItinerary(formattedData);
        }
        catch(error){
          console.log("Error fetching the data, reload and try again");
        }
        // console.log(destination,days,budget,selectedActivitiesString,companions,cuisine)
    }


    // const parseItinerary = (data) => {
    //   const formattedItinerary = {};
    
    //   Object.keys(data.itinerary).forEach((day) => {
    //     const dayInfo = data.itinerary[day];
    //     const activities = dayInfo.activities;
    //     const costEstimate = dayInfo.cost_estimate;
    
    //     formattedItinerary[day] = {
    //       Activities: activities,
    //       CostEstimate: costEstimate
    //     };
    //   });
    
    //   return formattedItinerary;
    // };
    
    const handleCheckboxChange = (event) => {
        const { id, checked } = event.target;
       
          setActivities({ ...activities, [id]: checked });
        
      }; 

      const handleRadioCompanionsChange =(event)=>{
        setCompanions(event.target.value);
      }
      const handleRadioCuisineChange =(event)=>{
        setCuisine(event.target.value);
      }

    useEffect(() => {
        console.log(activities);
      }, [activities]);
      

  return (
  <div>
    <div className="form-container">
      <div className="row">
        <div className="col-md-12">
          <h2>Travel Planner</h2>
          <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label for="destination">Destination</label>
            <input type="text" className="form-control" id="destination" value={destination} 
              onChange={
              (e) => setDestination(e.target.value)
              } placeholder="Enter destination"/>
          </div>
      <div className="form-group">
        <label for="noofdays">Number of Days</label>
        <input type="number" className="form-control" id="noofdays" value={days}
        onChange={
            (e) => setDays(e.target.value)
        } placeholder="Enter number of days"/>
      </div>
      <div className="form-group">
        <label for="budget">Budget</label>
        <input type="text" className="form-control" id="budget" value={budget}
        onChange={
            (e) => setBudget(e.target.value)
        } placeholder="Enter budget"/>
      </div>
      <div className="form-group">
        <label>Companions</label><br/>
        <div className="form-check form-check-inline">
          <input className="form-check-input" type="radio" name="companions" id="friends" value="friends" onChange={handleRadioCompanionsChange}/>
          <label className="form-check-label" for="friends">Friends</label>
        </div>
        <div className="form-check form-check-inline">
          <input className="form-check-input" type="radio" name="companions" id="family" value="family" onChange={handleRadioCompanionsChange}/>
          <label className="form-check-label" for="family">Family</label>
        </div>
        <div className="form-check form-check-inline">
          <input className="form-check-input" type="radio" name="companions" id="solo" value="solo" onChange={handleRadioCompanionsChange}/>
          <label className="form-check-label" for="solo">Solo</label>
        </div>
        <div className="form-check form-check-inline">
          <input className="form-check-input" type="radio" name="companions" id="couple" value="couple" onChange={handleRadioCompanionsChange}/>
          <label className="form-check-label" for="couple">Couple</label>
        </div>
      </div>
      <div className="form-group">
        <label>Activities</label>
        <br/>
        <div className="form-check form-check-inline">
          <input className="form-check-input" type="checkbox" id="sightseeing" 
          checked={activities.sightseeing} value="sightseeing" onChange={handleCheckboxChange}/>
          <label className="form-check-label" for="sightseeing">Sightseeing</label>
        </div>
        <div className="form-check form-check-inline">
          <input className="form-check-input" type="checkbox" id="beach" 
          checked={activities.beach} value="beach" onChange={handleCheckboxChange}/>
          <label className="form-check-label" for="beach">Beach</label>
        </div>
        <div className="form-check form-check-inline">
          <input className="form-check-input" type="checkbox" id="waterActivities"
          checked={activities.waterActivities} value="Water Activities" onChange={handleCheckboxChange}/>
          <label className="form-check-label" for="water">Water Activities</label>
        </div>
        <div className="form-check form-check-inline">
          <input className="form-check-input" type="checkbox" id="clubbing"
          checked={activities.clubbing} value="Clubbing"  onChange={handleCheckboxChange}/>
          <label className="form-check-label" for="clubbing">Clubbing</label>
        </div>
        <div className="form-check form-check-inline">
          <input className="form-check-input" type="checkbox" id="adventureActivities"
          checked={activities.adventureActivities}  value="Adventure Sports" onChange={handleCheckboxChange}/>
          <label className="form-check-label" for="adventure">Adventure Sports</label>
        </div>
        <div className="form-check form-check-inline">
          <input className="form-check-input" type="checkbox" id="festivalsEvents"
          checked={activities.festivalsEvents} value="Festivals/Events" onChange={handleCheckboxChange}/>
          <label className="form-check-label" for="festival">Festivals/Events</label>
        </div>
        <div className="form-check form-check-inline">
          <input className="form-check-input" type="checkbox" id="shopping"
          checked={activities.shopping}  value="shopping" onChange={handleCheckboxChange}/>
          <label className="form-check-label" for="shopping">Shopping</label>
        </div>
        <div className="form-check form-check-inline">
          <input className="form-check-input" type="checkbox" id="nightLife"
          checked={activities.nightLife} value="Night Life" onChange={handleCheckboxChange}/>
          <label className="form-check-label" for="nightlife">Night Life</label>
        </div>
      </div>

      <div className="form-group">
        <label>Cuisine</label><br/>
        <div className="form-check form-check-inline">
          <input className="form-check-input" type="radio" name="cuisine" id="vegetarian" value="vegetarian" onChange={handleRadioCuisineChange}/>
          <label className="form-check-label" for="vegetarian">Vegetarian</label>
        </div>
        <div className="form-check form-check-inline">
          <input className="form-check-input" type="radio" name="cuisine" id="nonveg" value="nonvegetarian" onChange={handleRadioCuisineChange}/>
          <label className="form-check-label" for="nonveg">Non Vegetarian</label>
        </div>
      </div>
      <button type="submit" >Submit</button>
    </form>
    {
      ithtml && (
        <div className="generated-itinerary">
          <div dangerouslySetInnerHTML={{__html: ithtml}}/>
        </div>
      )
    }
    {/* <div>
      {response.data}
    </div> */}
    {/* {itinerary && (
              <div>
                <h3>Generated Itinerary</h3>
                {Object.keys(itinerary).map((day) => (
                  <div key={day}>
                    <h4>{day}</h4>
                    <p>
                      <strong>Activities:</strong>{' '}
                      {itinerary[day].Activities.map((activity, index) => (
                        <span key={index}>{`${activity}, `}</span>
                      ))}
                    </p>
                    <p>
                      <strong>Cost estimate:</strong> {itinerary[day].CostEstimate} rupees
                    </p>
                  </div>
                ))}
              </div>
            )} */}

  </div>
</div>
</div></div>
  )
}

