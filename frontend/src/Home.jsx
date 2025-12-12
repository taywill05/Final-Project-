import { useNavigate } from "react-router-dom";
import "./Home.css";
import { GoogleGenAI } from "@google/genai";
import { useState, useEffect } from "react";
import { saveVibeCheck } from "./components/vibeApi";


const ai = new GoogleGenAI({
  apiKey: import.meta.env.VITE_GOOGLE_API_KEY,
});

function Home() {
  const navigate = useNavigate();

  const demoQuestions =
    "What color feels like you today?\n" +
    "Pick your energy: 🐢 🐇 🚀 🧘\n" +
    "Which fictional character matches your mood?\n";

  const username = localStorage.getItem("username") || "guest";
  const QUESTIONS_KEY = `vibeQuestions_${username}`;
  const ANSWERS_KEY = `vibeAnswers_${username}`;
  const RESULT_KEY = `vibeResult_${username}`;  

  const [questions, setQuestions] = useState([]);
  const [answers, setAnswers] = useState([]);
  const [loading, setLoading] = useState(false);
  const [saving, setSaving] = useState(false);

  
  const [vibeResult, setVibeResult] = useState(null);
  const [vibeLoading, setVibeLoading] = useState(false);


  const getQuestions = async () => {
    try {
      setLoading(true);

      const prompt =
        `Generate 3–5 creative, fun mood check-in questions similar to these examples:\n` +
        demoQuestions +
        `\nReturn only the questions, one per line.`;

      const response = await ai.models.generateContent({
        model: "gemini-2.0-flash",
        contents: prompt,
      });

      const text = response.text;
      console.log("AI output:", text);

      const questionList = text
        .split("\n")
        .map((q) => q.trim())
        .filter((q) => q.length > 0);

      setQuestions(questionList);
      setAnswers(new Array(questionList.length).fill(""));



      localStorage.setItem(QUESTIONS_KEY, JSON.stringify(questionList));
      localStorage.removeItem(ANSWERS_KEY);
      localStorage.removeItem(RESULT_KEY);
      setVibeResult(null);


    } catch (error) {
      console.error("Error generating questions:", error);

      const fallback = demoQuestions
        .split("\n")
        .map((q) => q.trim())
        .filter((q) => q.length > 0);

      setQuestions(fallback);
      setAnswers(new Array(fallback.length).fill(""));

      localStorage.setItem(QUESTIONS_KEY, JSON.stringify(fallback));
      localStorage.removeItem(ANSWERS_KEY);
      localStorage.removeItem(RESULT_KEY);
      setVibeResult(null);

    } finally {
      setLoading(false);
    }
  };

  
  const handleAnswerChange = (index, value) => {
    setAnswers((prev) => {
      const copy = [...prev];
      copy[index] = value;
      localStorage.setItem(ANSWERS_KEY, JSON.stringify(copy))
      return copy;
    });
  };

  
  const handleSubmitVibe = async () => {
    if (!vibeResult) {
      alert("Please generate your vibe first, then save it");
      return;
    }

    try {
      setSaving(true);

      await saveVibeCheck({
        questions,
        answers,
        vibeResult,
      });

      alert("Your vibe check was saved!");
    } catch(error) {
      console.error("Error saving vibe check:", error);
      alert("Could not save your vibe check. Please try again.");
    } finally {
      setSaving(false);
    }
};

  
  const handleGenerateVibe = async () => {
    try {
      setVibeLoading(true);

      const moodData = questions
        .map((q, idx) => `Q: ${q}\nA: ${answers[idx] || ""}`)
        .join("\n\n");

      const prompt = `
You are a playful but thoughtful mood coach.

Based on the following mood check answers, create:
1) A short creative vibe name (2–4 words)
2) A single emoji that matches the vibe
3) A short uplifting quote (1 sentence) in that vibe

Mood check:
${moodData}

Return ONLY valid JSON in this exact format:
{
  "vibeName": "string",
  "emoji": "🙂",
  "quote": "string"
}
`;

      const response = await ai.models.generateContent({
        model: "gemini-2.0-flash",
        contents: prompt,
      });

      let text = response.text || "";
      text = text.trim().replace(/```json/gi, "").replace(/```/g, "");


      const parsed = JSON.parse(text);


      const result = {
        vibeName: parsed.vibeName,
        emoji: parsed.emoji,
        quote: parsed.quote,
      };

      setVibeResult(result);

      localStorage.setItem(RESULT_KEY, JSON.stringify(result) );
    } catch (error) {
      console.error("Error generating vibe result:", error);
      alert("Could not generate your vibe result. Please try again.");
    } finally {
      setVibeLoading(false);
    }
  };

  useEffect(() => {
  const storedQuestions = JSON.parse(localStorage.getItem(QUESTIONS_KEY) || "null");
  const storedAnswers = JSON.parse(localStorage.getItem(ANSWERS_KEY) || "null");
  const storedVibe = JSON.parse(localStorage.getItem(RESULT_KEY) || "null");

  if (storedQuestions && Array.isArray(storedQuestions)) {
    setQuestions(storedQuestions);
    setAnswers(storedAnswers ?? new Array(storedQuestions.length).fill(""));
  } else {
    getQuestions();
  }

  if (storedVibe) {
    setVibeResult(storedVibe);
  }
}, []);;

  return (
    <div className="home-container">
      <h1 className="hero-title">How&apos;s Your Vibe Today?</h1>

      <div id="questions">
        {loading ? (
          <p>Loading questions...</p>
        ) : (
          <ul className="question-list">
            {questions.map((question, index) => (
              <li key={index} className="question-item">
                <p className="question-text">{question}</p>
                <textarea
                  className="question-answer"
                  placeholder="Type your answer here..."
                  value={answers[index] || ""}
                  onChange={(e) => handleAnswerChange(index, e.target.value)}
                />
              </li>
            ))}
          </ul>
        )}
      </div>

      
      <div className="question-actions">
        <button
          className="logMood-button"
          onClick={handleSubmitVibe}
          disabled={saving || loading}
        >
          {saving ? "Saving..." : "Save Vibe Check"}
        </button>

        <button
          className="logMood-button"
          onClick={handleGenerateVibe}
          disabled={vibeLoading || loading}
        >
          {vibeLoading ? "Creating Your Vibe..." : "Generate My Vibe"}
        </button>
        
        <button className= "logMood-button"
          onClick={() => {
          localStorage.removeItem(QUESTIONS_KEY);
          localStorage.removeItem(ANSWERS_KEY);
          localStorage.removeItem(RESULT_KEY);
          setVibeResult(null);
          getQuestions();
       }}>
        New Vibe Check
       </button>
      </div>

  
      {vibeResult && (
        <div className="vibe-card">
          <h2 className="vibe-title">
            {vibeResult.emoji} {vibeResult.vibeName}
          </h2>
          <p className="vibe-quote">“{vibeResult.quote}”</p>
        </div>
      )}

        

      <div className="stats-row">
        <div className="stat-card">
          <h3 className="stat-title">Mood Trend</h3>
          <p className="stat-value">View weekly vibe</p>
        </div>
        <div className="stat-card">
          <h3 className="stat-title">This Week&apos;s</h3>
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

