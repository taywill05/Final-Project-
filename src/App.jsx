import {useState} from 'react'
import './App.css'
import Home from './Home.jsx';
import DataDisplay from './DataDisplay.jsx';
import FormPage from './FormPage.jsx';
import MoodLog from './MoodLog.jsx';
import  {Routes, Route, Link, useLocation} from 'react-router-dom';

function App() {
  const location = useLocation();
  const hideNavbar = location.pathname === '/';

  return (
    <div>  
      {!hideNavbar && (// conditionally render navbar
     <nav className="navbar"> 
     {/* <div className="brand"><Link to= "/" > MoodSpace</Link> </div>*/}
       <div className="brand"> MoodSpace </div>
       <div className = "nav-links">
     
      {/*<Link to="/">Log In</Link>*/}
      <Link to="/home">Home</Link>
      <Link to="/data-display">MoodLog History</Link>
      <Link to= "/log-mood">Log Mood</Link>
      
  
      </div>
    </nav>
    )}
    <Routes>
      <Route path="/" element={<FormPage />} />
      <Route path="/home" element={<Home />} />
      <Route path="/data-display" element={<DataDisplay />} />
      <Route path="/log-mood" element={<MoodLog />} />
    </Routes> 
  </div>


  )

   
}

export default App
