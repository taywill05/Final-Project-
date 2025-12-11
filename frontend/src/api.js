import { apiSend, apiGet } from "./components/authApi";


function getCurrentUsername() {
  return localStorage.getItem("username");
}

export async function createMood(moodEntry) {
  const username = getCurrentUsername();
  if (!username) {
    throw new Error("No username found in localStorage. Please log in first.");
  }

  const body = {
    username,
    mood: moodEntry.mood,
    note: moodEntry.note ?? moodEntry.notes ?? "",
    emoji: moodEntry.emoji ?? "",
  };

  
  const data = await apiSend("/api/mood-posts", "post", body);
  return data;
}


export async function getMoods() {
  const username = getCurrentUsername();
  if (!username) {
    return [];
  }


  const posts = await apiGet(`/api/mood-posts/user/${username}`);

  return posts.map((p) => ({
    id: p.id,
    mood: p.mood,
    note: p.note,
    emoji: p.emoji,
    date: p.dateCreated,
  }));
}
