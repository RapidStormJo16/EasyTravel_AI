import React from 'react'
import './content.css'

import pImg1 from '../assets/11.jpg';
import pImg2 from '../assets/10.jpg';
import pImg3 from '../assets/14.jpg';
import pImg4 from '../assets/15.jpg';


export default function ContentData(props) {
  return (
    <div>
        <div className={props.className}>
          <div className="purpose">
            <h2>{props.heading}</h2>
            <p>{props.text}</p>
          </div>

          <div className="image">
            <img alt="pImg1" src={props.img1} />
            <img alt="pImg2" src={props.img2} />

          </div>
        </div>
    </div>
  )
}
