import api from "./authApi";

export async function saveVibeCheck(vibeItems) {
  
  const res = await api.post("/mood-posts/vibe-check", vibeItems);
  return res.data; 
}
