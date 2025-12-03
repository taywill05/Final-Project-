import { Link } from 'react-router-dom';
import './Home.css';
import { GoogleGenAI } from "@google/genai";
import { useState } from 'react';

function Home() {
    const demoQuestions ='What color feels like you today?\n' +
        'Pick your energy: 🐢 🐇 🚀 🧘\n' +
        'Which fictional character matches your mood?\n';

    const [questions, setQuestions] = useState([]);

    const ai = new GoogleGenAI({});

    async function getQuestions() {
        const response = await ai.models.generateContent({
            model: "gemini-2.5-flash",
            contents: `Generate 3 -5 questions like ${demoQuestions}`,
        });
        console.log(response.text);
        return response;
    }

    useEffect(async () => {
        await getQuestions().then((results)=>{

        })
    }, []);

    return (
        <div className ="home-container">
            <h1 className = "hero-title">How's Your Vibe Today?</h1>




            <div id="questions">

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