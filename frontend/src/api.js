import { apiSend, apiGet } from "./components/authApi";
import { moodToEmoji } from "./moodMap";


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
    emoji: moodEntry.emoji ?? moodToEmoji(moodEntry.mood),
  };

  
  const data = await apiSend("/api/mood-posts", "post", body);
  return data;
}

export async function updateMood(id, updatedFields) {
  const body = {
    mood: updatedFields.mood,
    note: updatedFields.note,
    emoji: updatedFields.emoji ??
      (updatedFields.mood ? moodToEmoji(updatedFields.mood) : undefined),
  };

  const data = await apiSend(`/api/mood-posts/${id}`, "put", body);
  return data;
}

export async function deleteMood(id) {
  await apiSend(`/api/mood-posts/${id}` , "delete");

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
