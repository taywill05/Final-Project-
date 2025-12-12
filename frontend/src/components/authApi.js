import axios from "axios";

export const API_URL = "http://localhost:8080";
export const TOKEN_KEY = "moodSpace_token";

const api = axios.create({
  baseURL: API_URL,
  headers: { "Content-Type": "application/json" },
});


api.interceptors.request.use((config) => {
  const token = localStorage.getItem(TOKEN_KEY);
  const isAuthEndpoint = 
  config.url.startsWith("/auth/login") ||
  config.url.startsWith("/auth/register");
  if (token && !isAuthEndpoint) {
  config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});


export async function apiSend(url, method, data = {}) {
  const res = await api({
    url,
    method,
    data,
  });
  return res.data;
}

export async function apiGet(url) {
  const res = await api.get(url);
  return res.data;
}


export async function apiLogin(username, password) {
  const res = await api.post("/auth/login", { username, password });
  return res.data;
}

export async function apiRegister(username, password) {
  const res = await api.post("/auth/register", { username, password });
  return res.data;
}

export function logout() {
  const username = localStorage.getItem("username");
  if (username) {
    const QUESTIONS_KEY = `vibeQuestions_${username}`;
    const ANSWERS_KEY = `vibeAnswers_${username}`;
    const RESULT_KEY = `vibeResult_${username}`;

    localStorage.removeItem(QUESTIONS_KEY);
    localStorage.removeItem(ANSWERS_KEY);
    localStorage.removeItem(RESULT_KEY);
  }
  localStorage.removeItem(TOKEN_KEY);   
  localStorage.removeItem("username");
}

export default api;
