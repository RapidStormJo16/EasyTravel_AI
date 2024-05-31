import { NavLink} from "react-router-dom";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import './navbar.css'
import { toast } from "react-toastify";

const Navbar=()=>{

  const navigate=useNavigate();
    useEffect(()=>{
        if(!localStorage.getItem('user')){
            navigate('/login');
        }
    },[navigate]);

  function logOut()
    {
        localStorage.clear();
        toast.success("Successfully logged out!");
        navigate('/login');
    }


    return(
        <nav class="navbar navbar-expand-lg bg-body-tertiary NavbarItems">
        <div class="container-fluid">
          {/* <div className="elements"> */}
          <a class="navbar-brand" href="/">EasyTravel.AI<i className="fa-solid fa-earth-americas"></i></a>
          <button className="navbar-toggler dark" id="button-toggle" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          {/* </div> */}
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0 2 nav-menu menu-icons">
              <li class="nav-item">
                <NavLink  to="/" className="nav-link"><i class="fa-solid fa-igloo"></i> Home</NavLink>
              </li>
              
              
              <li class="nav-item">
              <NavLink  to="/itinerary" className="nav-link"><i class="fa-solid fa-clipboard-list"></i> Personalized Itinerary</NavLink>
              </li>
              <li class="nav-item">
              <NavLink  to="/expense" className="nav-link"><i class="fa-solid fa-calculator"></i> Expense Tracker</NavLink>
              </li>
              <li class="nav-item">
              <NavLink  to="/suggestion" className="nav-link"><i class="fa-regular fa-paper-plane"></i> Suggestion Box</NavLink>
              </li>
              
            </ul>
            <form class="d-flex" role="search">
              <button class="btn btn-outline-danger me-2" type="submit" onClick={logOut}>Log Out</button>
              {/* <button class="btn btn-outline-secondary" type="submit">SignUp</button> */}

            </form>
          </div>
        </div>
      </nav>
        
    )
}

export default Navbar;