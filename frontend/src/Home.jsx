import { Link } from "react-router-dom";
import "./Home.css";
import { useState, useEffect } from "react";
import { GoogleGenerativeAI } from "@google/genai";

function Home() {
    const demoQuestions = `What color feels like you today?
Pick your energy: 🐢 🐇 🚀 🧘
Which fictional character matches your mood?`;

    const [questions, setQuestions] = useState("");

    const genAI = new GoogleGenerativeAI({
        apiKey: process.env.REACT_APP_GOOGLE_API_KEY
    });

    async function getQuestions() {
        const model = genAI.getGenerativeModel({ model: "gemini-2.5-flash" });
        const response = await model.generateContent(
            `Generate 3-5 questions like: ${demoQuestions}`
        );
        return response.response.text();
    }

    useEffect(() => {
        async function fetchQuestions() {
            const results = await getQuestions();
            setQuestions(results);
        }
        fetchQuestions();
    }, []);

    return (
        <div className="home-container">
            <h1 className="hero-title">How's Your Vibe Today?</h1>

            <div id="questions">{questions}</div>

            <Link to="/log-mood">
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
            </Link>
        </div>
    );
}

export default Home;