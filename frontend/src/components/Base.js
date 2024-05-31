import Navbar from "./navbar/Navbar";

const Base=({title="Welcome to our website",children})=>{
    return(
        <div className="container-fluid">
            <Navbar/>

            {children}
        </div>
    )
}

export default Base;