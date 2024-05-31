import "./form.css"
import React, { useState } from 'react';
import axios from 'axios';
import { toast } from 'react-toastify';

export default function SuggestionForm() {
  
    const [suggestion, setSuggestion] = useState({
        userId: localStorage.getItem('user'),
        content: ''
    });

    const handleInputChange = (e) => {
        setSuggestion({ ...suggestion, content: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post('http://localhost:8010/suggestions/feedback', suggestion);
            toast.success('Suggestion submitted successfully!');
            setSuggestion({ ...suggestion, content: '' }); // Clear the textarea after submission
        } catch (error) {
            console.error('Error submitting suggestion:', error);
            toast.error('Failed to submit suggestion. Please try again.');
        }
    };

    return (
    
    <div className="form-container feedback">
        <h1> Looking forward to hear from you!</h1>
        <form onSubmit={handleSubmit}>
            <textarea placeholder="Write your feedback" rows="4"
            value={suggestion.content} onChange={handleInputChange} required></textarea>
            <button type="submit">Send Message</button>
        </form>
    </div>
  )
}
