import { useEffect, useState } from "react";
import "./DataDisplay.css";
import { getMoods } from "./api";

function moodToEmoji(mood) {
  const moodMap = {
    happy: "😊",
    sad: "😔",
    angry: "😡",
    surprised: "😲",
    neutral: "😐",
    excited: "🤩",
    anxious: "😰",
    grateful: "🙏",
    tired: "😴",
    stressed: "😣",
    confused: "😕",
    bored: "😐",
    lonely: "😞",
    hopeful: "🌈",
    relaxed: "😌",
    proud: "😎",
    curious: "🤔",
    motivated: "💪",
    overwhelmed: "😵",
    joyful: "😂",
    peaceful: "☮️",
    frustrated: "😤",
    irritated: "😑",
  };

  if (!mood) return "❓";
  const key = mood.toLowerCase();
  return moodMap[key] || "❓";
}

function DataDisplay() {
  const [filter, setFilter] = useState("all");
  const [entries, setEntries] = useState([]); // start empty
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    handleRefresh();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const handleRefresh = async () => {
    try {
      setLoading(true);
      const data = await getMoods();
      setEntries(data);
    } catch (error) {
      console.error("Error fetching mood data:", error);
    } finally {
      setLoading(false);
    }
  };

  const filteredEntries =
    filter === "all"
      ? entries
      : entries.filter(
          (entry) =>
            entry.mood && entry.mood.toLowerCase() === filter
        );

  if (loading) {
    return (
      <div className="history-container">
        <h1 className="history-title">Your Mood History</h1>
        <div className="loading">Loading mood data...</div>
      </div>
    );
  }

  return (
    <div className="history-container">
      <h1 className="history-title">Your Mood History</h1>

      <div className="history-toolbar">
        <span>Filter:</span>
        <select
          className="history-filter"
          value={filter}
          onChange={(e) => setFilter(e.target.value)}
        >
          <option value="all">All</option>
          <option value="happy">😊 Happy</option>
          <option value="sad">😔 Sad</option>
          <option value="angry">😡 Angry</option>
          <option value="surprised">😲 Surprised</option>
          <option value="neutral">😐 Neutral</option>
          <option value="excited">🤩 Excited</option>
          <option value="anxious">😰 Anxious</option>
          <option value="grateful">🙏 Grateful</option>
          <option value="tired">😴 Tired</option>
          <option value="stressed">😣 Stressed</option>
          <option value="confused">😕 Confused</option>
          <option value="bored">😐 Bored</option>
          <option value="lonely">😞 Lonely</option>
          <option value="hopeful">🌈 Hopeful</option>
          <option value="relaxed">😌 Relaxed</option>
          <option value="proud">😎 Proud</option>
          <option value="curious">🤔 Curious</option>
          <option value="motivated">💪 Motivated</option>
          <option value="overwhelmed">😵 Overwhelmed</option>
          <option value="joyful">😂 Joyful</option>
          <option value="peaceful">☮️ Peaceful</option>
          <option value="frustrated">😤 Frustrated</option>
          <option value="irritated">😑 Irritated</option>
        </select>
      </div>

      <div className="history-card">
        <div className="history-row history-row-header">
          <span>Date</span>
          <span>Mood</span>
          <span>Notes</span>
        </div>

        {filteredEntries.map((entry) => (
          <div key={entry.id} className="history-row">
            <span className="history-date">
              {entry.date
                ? new Date(entry.date).toLocaleString()
                : "No date"}
            </span>

            <span className="history-mood">
              <span className="history-emoji">
                {moodToEmoji(entry.mood)}
              </span>
              {entry.mood || "Unknown"}
            </span>

            <span className="history-notes">
              {entry.note || "(no notes)"}
            </span>
          </div>
        ))}

        {filteredEntries.length === 0 && (
          <div className="history-empty">
            No entries for this filter yet.
          </div>
        )}
      </div>

      <button className="refresh-button" onClick={handleRefresh}>
        Refresh Data
      </button>
    </div>
  );
}

export default DataDisplay;
