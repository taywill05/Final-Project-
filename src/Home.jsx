import { Link } from 'react-router-dom';
import './Home.css';

function Home() {
    return (
        <div className ="home-container">
            <h1 className = "hero-title">How's Your Vibe Today?</h1>

            <div className ="mood-row">
                <button className="mood-button">
                    <span className ="mood-emoji">😊</span>
                    <span className ="mood-label"> Happy</span>
                </button>

                <button className="mood-button">
                    <span className ="mood-emoji">😔</span>
                    <span className ="mood-label"> Sad</span>
                </button>

                <button className="mood-button">
                    <span className ="mood-emoji">😡</span>
                    <span className ="mood-label"> Angry</span>
                </button>

                <button className="mood-button">
                    <span className ="mood-emoji">😲</span>
                    <span className ="mood-label"> Surprised</span>
                </button>
            </div>

                <Link to="/log-mood">
                    <button className="logMood-button">
                        Log Your Mood
                    </button>
                </Link>

            <div className="stats-row">
                <div className="stat-card">
                    <h3 className="stat-title">Mood Trend</h3>
                    <p className="stat-value">View weekly vibe</p>
                </div>

                <div className="stat-card">
                    <h3 className="stat-title">This Week's</h3>
                    <p className="stat-value">Balance</p>
                </div>

                <div className="stat-card">
                    <h3 className="stat-title">Streak</h3>
                    <p className="stat-value">Keep it going 🔥</p>

                </div>
            </div>

        
                
    
                
        </div>

    );
}

export default Home;