import Base from "../Base"
import ItineraryForm from "./ItineraryForm";
import Hero from "../Hero";
import aboutImg from "../../assets/2.jpg";
import Footer from "../Footer";
import ItineraryContent from "./ItineraryContent";

const Itinerary=()=>{
    return(
        <Base>
        <Hero
        cName="hero-mid"
        heroImg={aboutImg}
        title="Personalized Itinerary"
        // text="Using the power of Artificial Intelligence, you will be able to make personalized itinerary in few seconds"
        btnClass="hide"
        />
        <ItineraryContent/>
        <ItineraryForm/>
        <Footer/>
        </Base>
    )
}

export default Itinerary;

