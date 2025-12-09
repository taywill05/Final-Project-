// src/api.js
import { apiSend, apiGet,apiLogin, apiRegister } from "./components/authApi";

const TOKEN_KEY = "moodSpace_token";

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

export async function login(username, password) {
  const data = await apiLogin(username,password);

  if(data.token) {
    localStorage.setItem(TOKEN_KEY, data.token)
  }
  return data; 
}

export async function signup(username, password) {
  const response = await apiRegister(username, password);

  if(data.token){
    localStorage.setItem(TOKEN_KEY, data.token);
  }
  return data;
  
}