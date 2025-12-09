
import { apiSend } from "./authApi"; 

export async function saveVibeCheck(questions, answers) {
  const payload = questions.map((q, idx) => ({
    question: q,
    answer: answers[idx] || "",
  }));

 
  return apiSend("/mood/vibe-check", "post", payload);
}
