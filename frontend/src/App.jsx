import { useState } from 'react';
import './App.css';
import Home from './Home.jsx';
import DataDisplay from './DataDisplay.jsx';
import FormPage from './FormPage.jsx';
import MoodLog from './MoodLog.jsx';
import { Routes, Route, Link, useLocation, Navigate } from 'react-router-dom';

function App() {
  const location = useLocation();
  const hideNavbar = location.pathname === '/login';

  return (
    <div>
  
      {!hideNavbar && (
        <nav className="navbar">
          <div className="brand">MoodSpace</div>
          <div className="nav-links">
            <Link to="/home">Home</Link>
            <Link to="/data-display">MoodLog History</Link>
            <Link to="/log-mood">Log Mood</Link>
          </div>
        </nav>
      )}

      <Routes>
      
        <Route path="/" element={<Navigate to="/login" />} />
        <Route path="/login" element={<FormPage />} />
        <Route path="/home" element={<Home />} />
        <Route path="/data-display" element={<DataDisplay />} />
        <Route path="/log-mood" element={<MoodLog />} />
      </Routes>
    </div>
  );
}

export default App;
