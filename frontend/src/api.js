// src/api.js
import { apiSend, apiGet } from "./components/authApi";

// Create / POST a mood (optional helper if you want to use it)
export async function createMood(moodEntry) {
  // This will call: POST http://localhost:8080/mood/add
  const res = await apiSend("/mood/add", "post", moodEntry);
  return res;
}

// Get / GET all moods for history
export async function getMoods() {
  // This will call: GET http://localhost:8080/mood/data-display
  const res = await apiGet("/mood/data-display");
  return res;
}

// Fake LOGIN (if you're using it)
export async function login(username, password) {
  console.log("Pretending to log in:", username);

  const validUsername = "mooduser";
  const validPassword = "mood123";

  return new Promise((resolve, reject) => {
    setTimeout(() => {
      if (username === validUsername && password === validPassword) {
        resolve({ username });
      } else {
        reject(new Error("Invalid username or password"));
      }
    }, 600);
  });
}
