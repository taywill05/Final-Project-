import { createMood } from "../api";

export async function saveVibeCheck(data) {
  const {questions, answers, vibeResult} = data;
  const {vibeName, emoji, quote} = vibeResult;

  const noteLines = questions.map((q, i) => {
    const ans = answers[i] || "";
    return `Q: ${q}\nA: ${ans}`;
  });

  const noteText = [
    "Vibe Chech Q&A:",
    ...noteLines,
  ].join("\n");

  const moodEntry = {
    mood: vibeName,
    emoji: emoji,
    note: noteText,
  };
  return createMood(moodEntry);
}

  
