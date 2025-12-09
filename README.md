### 🌈 App Concept: “MoodSpace” — Your Daily Mood + Vibe Tracker

### 🎯 Core Purpose
To help users reflect on their emotional state and personality “vibe” each day using emojis, quirky prompts, and visual logs. It’s part journal, part personality mirror.

### 🧩 Key Features
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

### 🛠️ Tech Stack
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

### Architecture Diagram
<img width="4032" height="2215" alt="image" src="https://github.com/user-attachments/assets/4249821a-a008-414d-827d-f144de5b2acd" />


**### 🔗 API Endpoints**

| **Method**    | **Route**        |   **Purpose**                 |
|------------|-----------------------|--------------------------------|
| **GET**    | `/moods`              | Go to Home Screen              |
| **POST**   | `/moods`              | Create a new mood entry        |
| **GET**    | `/moods/data-display` | Fetch all mood entries         |
| **PUT**    | `/moods/{id}`         | Update a mood entry            |


AI Usage Log Template
Project Information
- Course: COP 3060 – Full Stack Web Development
- Milestone #: 1
- Team Name: Fantastic Four
- Team Members:

Logan Boone	 			Taylor Williams 
Breanna Taffe 			Shemaiah Lester

- Date Submitted:11/18/2025


**Creating updated table**

I’ll create the updated table with two new rows based on the user’s input. Since they didn’t specify dates, I’ll use 11/12 for both rows to keep it consistent. The first new row will be about using ChatGPT to generate test data for databases, and the second will be about integrating the Gemini API for user engagement. I’ll make sure each cell is concise and follows the formatting rules, including bold labels where it enhances readability. Let’s get this table ready!
| **Date** | **AI Tool** | **Prompt (Summary)** | **Purpose** | **Output Used (Y/N)** | **Human Revisions** | **Ethical Reflection** |
|---|---|---|---:|:---:|---|---|
| 11/12 | Copilot | Describe some simple app ideas for an intermediate programmer | Feature Discovery | Y | Combined the generated vibe-check idea with the mood-journal feature to use emojis to log daily mood; refined wording and UX flow | Used AI to identify app topics as a jumping-off point for multiple features |
| 12/3 | ChatGPT | Generate realistic test data for project databases | Test data generation | Y | Cleaned and normalized generated records; adjusted value distributions and removed unrealistic entries | Ensured no real PII was used; preferred synthetic data to protect privacy |
| 12/8 | Gemini API | Ask users random vibe questions and return a funny vibe + matching quote | User engagement / Vibe check | Y | Integrated prompts into app flow; tuned question order and response tone for clarity and safety | Considered consent and transparency; avoided manipulative phrasing and biased or harmful suggestions |



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
