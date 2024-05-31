package com.ai.generation.service;

import org.springframework.ai.client.AiClient;
import org.springframework.ai.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.generation.exception.GenerationException;

//import org.springframework.ai.client;

@Service
public class GenerationService {
	@Autowired
	AiClient aiClient;

	public String getItinerary(String destination, int noOfDays, int budget, String companions, String activities, String cuisine){
        
		try{PromptTemplate promptTemplate = new PromptTemplate("""
                You are an expert in planning out a trips, like a trip planning wizard. With the best of your abilities,
                Generate a personalized travel itinerary for a trip to {destination}
				for {noOfDays} number of days, with a budget of {budget} rupees.
				The traveler is interested in a {companions} vacation and enjoys {activities}.
				The itinerary should include all the activities mentioned and {cuisine} dining options.
				Also mention the cost estimate for travelling, activites, food, etc, inside the itinerary. 
				Give the data in the html format which could be used for using dangerouslySetInnerHtml method.
				Be aware of the below format as well and give it in a similar fashion:
				<div>
  
  				<h1>Personalized Travel Itinerary for Kochi</h1>
  				<h2>Day 1: Arrival in Kochi</h2>
  				<p>Arrive in Kochi and check into a budget-friendly hotel. Spend the evening exploring Fort Kochi and watching the sunset at the Chinese Fishing Nets.</p>
  				<p><strong>Cost Estimate:</strong> Hotel - 2000 rupees</p>
  				
  				<h2>Day 2: Sightseeing in Kochi</h2>
  				<p>Visit the Mattancherry Palace, Paradesi Synagogue, and St. Francis Church. Enjoy a traditional Kerala lunch at a local restaurant.</p>
  				<p><strong>Cost Estimate:</strong> Entrance fees - 500 rupees, Lunch - 500 rupees</p>
  				
  				<h2>Day 3: Beach Day at Cherai Beach</h2>
  				<p>Spend the day relaxing on the beautiful Cherai Beach. Enjoy water sports activities like swimming and beach volleyball.</p>
  				<p><strong>Cost Estimate:</strong> Transportation to beach - 1000 rupees, Beach activities - 1000 rupees</p>
  				
  				<h2>Day 4: Explore the Backwaters</h2>
  				<p>Take a boat tour of the famous Kerala backwaters. Enjoy the serene beauty of the backwaters and watch the sunset over the water.</p>
  				<p><strong>Cost Estimate:</strong> Boat tour - 1500 rupees</p>
  
  
				<h3>Total Cost Estimate for 7 Days: 10000 rupees</h3>
				</div>
               """);
        
//        String prompt = "";
       
        promptTemplate.add("destination", destination);
        promptTemplate.add("noOfDays", noOfDays);
        promptTemplate.add("budget", budget);
        promptTemplate.add("companions", companions);
        promptTemplate.add("activities", activities);
        promptTemplate.add("cuisine", cuisine);
        
        return this.aiClient.generate(promptTemplate.create()).getGeneration().getText();}
	catch(Exception e) {
		throw new GenerationException("Error generating itinerary"+e.getMessage());
	}
    }
}
