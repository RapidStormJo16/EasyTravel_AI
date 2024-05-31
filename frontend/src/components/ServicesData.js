import React from 'react'
import { NavLink, Link } from 'react-router-dom'
import './services.css'
export default function ServicesData(props) {
  return (
    <div className="s-card">
        <div className="s-image">
          <a href={props.link}>
              <img src={props.image} alt="sImg" />
        </a> 
        </div>
        <h4>{props.heading}</h4>
        <p>{props.text}</p>
    </div>
  )
}
