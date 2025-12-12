import './App.css';
import Home from './Home.jsx';
import DataDisplay from './DataDisplay.jsx';
import FormPage from './FormPage.jsx';
import MoodLog from './MoodLog.jsx';
import Navbar from "./Navbar.jsx";
import { Routes, Route, useLocation, Navigate } from 'react-router-dom';

function ProtectedRoute({ children }) {
  const token = localStorage.getItem("moodSpace_token");
  return token ? children : <Navigate to="/login" />;
}

function App() {
  const location = useLocation();
  const hideNavbar = location.pathname === '/login';

  return (
    <div>
  
      {!hideNavbar && <Navbar />}
        
      <Routes>
      
        <Route path="/" element={<Navigate to="/login" />} />
        <Route path="/login" element={<FormPage />} />

        <Route path="/home" element={<ProtectedRoute><Home /></ProtectedRoute>} />
        <Route path="/data-display" element={<ProtectedRoute><DataDisplay /></ProtectedRoute>} />
        <Route path="/log-mood" element={<ProtectedRoute><MoodLog /></ProtectedRoute>} />
      </Routes>
    </div>
  );
}

export default App;
