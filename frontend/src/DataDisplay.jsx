import { useEffect, useState } from "react";
import "./DataDisplay.css";
import { getMoods, updateMood, deleteMood } from "./api";
import { MOOD_MAP, moodToEmoji } from "./moodMap";


function DataDisplay() {
  const [filter, setFilter] = useState("all");
  const [entries, setEntries] = useState([]);
  const [loading, setLoading] = useState(true);

  const [editingId, setEditingId] = useState(null);
  const [editNote, setEditNote] = useState("");
  const [editMoodKey, setEditMoodKey] = useState("");

  useEffect(() => {
    handleRefresh();
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

  const startEdit = (entry) => {
    setEditingId(entry.id);
    setEditNote(entry.note || "");
    setEditMoodKey(entry.mood?.toLowerCase() || "");
  };

  const cancelEdit = () => {
    setEditingId(null);
    setEditNote("");
    setEditMoodKey("");
  };

  const saveEdit = async (id, originalMood) => {
    const newMoodKey = editMoodKey || originalMood?.toLowerCase();
    const newMood = newMoodKey;
    const newEmoji = MOOD_MAP[newMoodKey] || moodToEmoji(originalMood);
    try {
      await updateMood(id, {
        mood:newMood,
        note: editNote,
        emoji: newEmoji,
      });
      await handleRefresh();
      cancelEdit();
    } catch (error) {
      console.error("Error updating mood", error);
      alert("Could not update this mood entry.");
    }
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Delete this mood log?")) return;
    try {
      await deleteMood(id);
      await handleRefresh();
    } catch (error) {
      console.error("Error deleting mood:", error);
      alert("Could not delete this mood entry.");
    }
  };

  const filteredEntries =
    filter === "all"
      ? entries
      : entries.filter(
          (entry) => entry.mood && entry.mood.toLowerCase() === filter
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
        </select>
      </div>

      <div className="history-card">
        <div className="history-row history-row-header">
          <span>Date</span>
          <span>Mood</span>
          <span>Notes</span>
          <span>Actions</span>
        </div>

        {filteredEntries.map((entry) => {
          const isEditing = editingId === entry.id;

          return (
            <div key={entry.id} className="history-row">
              <span className="history-date">
                {entry.date
                  ? new Date(entry.date).toLocaleString()
                  : "No date"}
              </span>

              <span className="history-mood">
                  {isEditing ? (
                    <select
                      className="history-edit-emoji"
                      value={editMoodKey}
                      onChange={(e) => setEditMoodKey(e.target.value)}
                    >
                      <option value="">Choose emoji</option>
                      {Object.entries(MOOD_MAP).map(([moodKey, emoji]) => (
                        <option key={moodKey} value={moodKey}>
                          {emoji} {moodKey}
                        </option>
                      ))}
                    </select>
                  ) : (
                    <>
                      <span className="history-emoji">
                        {entry.emoji || moodToEmoji(entry.mood)}
                      </span>
                      {entry.mood || "Unknown"}
                    </>
                  )}
                </span>

                <span className="history-notes">
                  {isEditing ? (
                    <textarea
                      className="history-edit-note"
                      value={editNote}
                      onChange={(e) => setEditNote(e.target.value)}
                    />
                  ) : (
                    <div>{entry.note || "(no notes)"}</div>
                  )}
                </span>


              <span className="history-actions">
                {isEditing ? (
                  <>
                    <button
                      onClick={() => saveEdit(entry.id, entry.mood)}
                    >
                      Save
                    </button>
                    <button onClick={cancelEdit}>Cancel</button>
                  </>
                ) : (
                  <>
                    <button onClick={() => startEdit(entry)}>Edit</button>
                    <button onClick={() => handleDelete(entry.id)}>
                      Delete
                    </button>
                  </>
                )}
              </span>
            </div>
          );
        })}

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
