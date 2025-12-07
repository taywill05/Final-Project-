🌈 App Concept: “MoodSpace” — Your Daily Mood + Vibe Tracker

🎯 Core Purpose
To help users reflect on their emotional state and personality “vibe” each day using emojis, quirky prompts, and visual logs. It’s part journal, part personality mirror.
🧩 Key Features
1. Daily Vibe Check
Answer 3–5 fun, rotating questions like:
“What color feels like you today?”
“Pick your energy: 🐢 🐇 🚀 🧘”
“Which fictional character matches your mood?”
Based on answers, assign a vibe label (e.g., “Zen Wizard”, “Chaotic Good”, “Lowkey Royalty”).
2. Emoji Mood Journal
Select up to 3 emojis to represent your mood (e.g., 😤😴🍕).
Optional: Add a short note or caption.
Auto-tag recurring emojis and show trends over time.
3. Vibe History Timeline
Scrollable calendar or timeline view of past vibes and emoji logs.
Color-coded entries (e.g., warm tones for energetic days, cool tones for calm ones).
Tap to expand and read past entries.

🛠️ Tech Stack
Layer
Technology
Role
Frontend
React
UI for mood logging, vibe questions, analytics
Backend
Spring Boot
REST API for user data, vibe logic, emoji logs
Database
PostgreSQL or MySQL
Stores user entries, emoji tags, vibe labels
Optional API
OpenAI / Spotify
Caption generation / vibe-based music suggestions

🔗 API Endpoints



































MethodRoutePurposeGET/moodsFetch all mood entriesPOST/moodsCreate a new mood entryGET/moods/{id}Fetch a specific mood entryPUT/moods/{id}Update a mood entry

AI Usage Log Template
Project Information
- Course: COP 3060 – Full Stack Web Development
- Milestone #: 1
- Team Name: Fantastic Four
- Team Members:

Logan Boone	 			Taylor Williams 
Breanna Taffe 			Shemaiah Lester

- Date Submitted:11/18/2025

AI Tool Usage Table
Date
AI Tool
Prompt(Summary)
Purpose
Output Used Y/N
Human Revisions
Ethical Reflection
11/12
Copilot
Describe some simple app ideas for an intermediate programmer?
Feature Discovery
Y
Combined the generated vibe check idea with the mood journal feature to use emojis to log daily mood. 
We used AI to help us identify some general app topics to build app features around. This AI provided us with a jumping-off point for an app idea that we could create several features around.

Summary of AI Use
AI was utilized to help generate app ideas across various categories, which were then analyzed as potential app options


# React + Vite

This template provides a minimal setup to get React working in Vite with HMR and some ESLint rules.

Currently, two official plugins are available:

- [@vitejs/plugin-react](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react) uses [Babel](https://babeljs.io/) (or [oxc](https://oxc.rs) when used in [rolldown-vite](https://vite.dev/guide/rolldown)) for Fast Refresh
- [@vitejs/plugin-react-swc](https://github.com/vitejs/vite-plugin-react/blob/main/packages/plugin-react-swc) uses [SWC](https://swc.rs/) for Fast Refresh

## React Compiler

The React Compiler is not enabled on this template because of its impact on dev & build performances. To add it, see [this documentation](https://react.dev/learn/react-compiler/installation).

## Expanding the ESLint configuration

If you are developing a production application, we recommend using TypeScript with type-aware lint rules enabled. Check out the [TS template](https://github.com/vitejs/vite/tree/main/packages/create-vite/template-react-ts) for information on how to integrate TypeScript and [`typescript-eslint`](https://typescript-eslint.io) in your project.
