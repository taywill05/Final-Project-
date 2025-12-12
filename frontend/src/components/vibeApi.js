import { apiSend } from "./authApi";

function getCurrentUsername() {
  return localStorage.getItem("username");
}

export async function saveVibeCheck({ questions, answers, vibeResult }) {
  const username = getCurrentUsername();
  if (!username) throw new Error("No username found. Please log in.");

  return apiSend("/api/vibe-checks", "post", {
    username,
    questions,
    answers,
    vibeName: vibeResult.vibeName,
    emoji: vibeResult.emoji,
    quote: vibeResult.quote,
  });
}
