// src/api.js
// Temporary in-memory "backend" for MoodSpace

let moods = []; // this will live as long as the dev server is running

// Create / POST a mood
export async function createMood(moodEntry) {
  console.log("Pretending to send mood to backend:", moodEntry);

  const newEntry = {
    id: Date.now(),
    ...moodEntry,
  };

  moods.push(newEntry);

  // simulate small delay
  return new Promise((resolve) => {
    setTimeout(() => resolve(newEntry), 300);
  });
}

// Get / GET all moods
export async function getMoods() {
  // simulate small delay
  return new Promise((resolve) => {
    setTimeout(() => resolve(moods), 300);
  });
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
