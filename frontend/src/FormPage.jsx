import {useState} from 'react';
import { Link, useNavigate} from 'react-router-dom';
import './FormPage.css';
import { FaLock, FaUser } from "react-icons/fa";
import {login} from './api';


function FormPage() {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [status, setStatus] = useState(null);
    const [loading, setLoading] = useState(false);
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setLoading(true);
        setStatus(null);

        try {
            await login(username, password);
            setStatus('Login successful! Redirecting...');
            setTimeout(() => {
            navigate('/home');  
            }, 2000);
        }

         catch (error) {
            console.error(error);
            setStatus('Login failed. Please check your credentials and try again.');
        }
        finally {
            setLoading(false);
        }
    };

    return (
   <div className="formpage-container">
    <div className= "form-box login">
        <form onSubmit={handleSubmit}>
        <h1>Login</h1>
        
            <div className="input-group">
                <input type="text" id="username" name="username" 
                placeholder="Username" required 
                value={username} onChange={(e) => setUsername(e.target.value)} />
                <FaUser  className ="icon"/>
            </div>

            <div className="input-group">
                <input type="password" id="password" name="password"
                placeholder="Password" required 
                value={password} onChange={(e) => setPassword(e.target.value)} />
                <FaLock  className ="icon"/>
            </div>

            <div className="remember-forgot">
                <label>
                    <input type="checkbox" /> Remember me
                </label>
                <a href="#">Forgot Password?</a>
            </div>
            <button type="submit" className="submit-button" disabled={loading}>
                {loading ? 'Logging in...' : 'Login'}
            </button>
            {status && <div className="status-message">{status}</div>}
            <p className="signup-link">
                Don't have an account? <a href="#">Sign Up</a>
            </p>
            
        </form>     
    </div>
   </div>
    );
}   

export default FormPage;