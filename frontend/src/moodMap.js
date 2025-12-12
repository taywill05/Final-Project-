export const MOOD_MAP = {
  happy: "😊",
  sad: "😔",
  angry: "😡",
  surprised: "😲",
  neutral: "😐",
  excited: "🤩",
  anxious: "😰",
  grateful: "🙏",
  tired: "😴",
  stressed: "😣",
  confused: "😕",
  bored: "😐",
  lonely: "😞",
  hopeful: "🌈",
  relaxed: "😌",
  proud: "😎",
  curious: "🤔",
  motivated: "💪",
  overwhelmed: "😵",
  joyful: "😂",
  peaceful: "☮️",
  frustrated: "😤",
  irritated: "😒",
};

export function moodToEmoji(mood) {
  if (!mood) return "❓";
  const key = mood.toLowerCase();
  return MOOD_MAP[key] || "❓";
}
