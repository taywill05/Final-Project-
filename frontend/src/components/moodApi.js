// src/components/moodApi.js
import api from "./authApi";

export async function createMoodPost(username, mood, note, emoji) {
  const res = await api.post("/mood-posts", {
    username,
    mood,
    note,
    emoji,
  });
  return res.data; // MoodPostResponse
}

export async function getMoodPostsForUser(username) {
  const res = await api.get(`/mood-posts/user/${username}`);
  return res.data; // array of MoodPostResponse
}
