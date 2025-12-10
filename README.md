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


| **Endpoint** | **Method** | **Explanation** |
|---|---:|---|
| `/mood/` | `GET` | Public welcome/health endpoint that returns a short message; no authentication required. |
| `/mood/add` | `POST` | Authenticated endpoint that accepts a `CreateMood` JSON payload and saves it for the current user (use `Principal` or `@AuthenticationPrincipal` to associate the entry). |
| `/mood/data-display` | `GET` | Authenticated endpoint that returns mood history; for regular users return only their entries, for admins return all entries (authorize with roles and scope the response server-side). |
| `/mood/vibe-check` | `POST` | Authenticated endpoint that accepts a `List<VibeCheckItem>` JSON payload, validates/sanitizes it, and persists the vibe-check associated with the authenticated user. |



AI Usage Log Template
Project Information
- Course: COP 3060 – Full Stack Web Development
- Milestone #: 1
- Team Name: Fantastic Four
- Team Members:

Logan Boone	 			Taylor Williams 
Breanna Taffe 			Shemaiah Lester

- Date Submitted:11/18/2025




### 🤖 AI Tool Usage Log

| **Date** | **AI Tool** | **Prompt (Summary)** | **Purpose** | **Output Used (Y/N)** | **Human Revisions** | **Ethical Reflection** |
|---|---:|---|---:|:---:|---|---|
| 11/12 | Copilot | Describe some simple app ideas for an intermediate programmer | Feature Discovery | Y | Combined the generated vibe-check idea with the mood-journal feature to use emojis to log daily mood; refined wording and UX flow | Used AI to identify app topics as a jumping-off point for multiple features |
| 11/12 | **ChatGPT** | Generate realistic test data for project databases | Test data generation | Y | Removed real-looking PII; replaced username/password with synthetic placeholders; hashed password-like strings; normalized and sanitized mood entries; archived original output in a secure, access-restricted location | Model produced sensitive-looking fields (username, password, first/last name, mood history); we redacted and replaced those fields with synthetic non-identifying values, hashed credentials, added a policy to never accept/store plaintext credentials from AI outputs, validated distributions and checked for bias, and documented provenance and retention policy |
| 11/12 | Gemini API | Ask users random vibe questions and return a funny vibe + matching quote | User engagement / Vibe check | Y | Integrated prompts into app flow; tuned question order and response tone for clarity and safety | Considered consent and transparency; avoided manipulative phrasing and biased or harmful suggestions |
| 11/12 | Copilot | Explain database architecture used and typical use cases for each component | Documentation / Architecture explanation | Y | Incorporated Copilot’s descriptions into the architecture page; clarified roles for primary DB, read-replica, cache, and analytics store; added diagrams and short usage examples | Ensured descriptions avoid implying AI-designed security guarantees; validated recommended patterns with engineering lead before publishing |
| 11/12 | **ChatGPT** | Suggest emoji set and mappings for the architecture page (icons for DB, cache, queue, analytics) | UI / Visual design guidance | Y | **Used the emojis that best represented the architecture that was used** | **Validated emoji choices for clarity, accessibility, and cultural neutrality; documented fallback text for screen readers and avoided symbols that could be misinterpreted or stigmatizing.** |
| 11/12 | Copilot | Guide on how to add an appendix to the AI usage log | Process / Compliance | Y | Used Copilot’s step-by-step guidance to create Appendix A–C structure; added sanitized prompts, redaction logs, and cross-reference IDs to the appendix index | Followed principle of minimizing exposed sensitive data; archived originals in restricted storage and included redaction notes and retention policy in the appendix |





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
