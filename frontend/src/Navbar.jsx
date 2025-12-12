import { Link, useNavigate } from "react-router-dom";
import { logout } from "./components/authApi";


function Navbar() {
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();                   
    navigate("/login");        
  };

  return (
    <nav className="navbar">
      <div className="brand">MoodSpace</div>

      <div className="nav-links">
        <Link to="/home">Home</Link>
        <Link to="/data-display">MoodLog History</Link>
        <Link to="/log-mood">Log Mood</Link>

        <button className="logout-button" onClick={handleLogout}>
          Logout
        </button>
      </div>
    </nav>
  );
}

export default Navbar;
