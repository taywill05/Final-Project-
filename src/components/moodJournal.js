import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080/moods',
    headers: {"Content-Type": "application/json"},
});

// Attach JWT from localStorage before each request
api.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem("contactlist_token");
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    (error) => Promise.reject(error)
);

api.interceptors.response.use(
    (response) => response,
    (error) => {
        if (error.response && error.response.status === 401) {
            if (!window.location.pathname.startsWith("/login")) {
                window.location.href = "/login";
            }
        }
        return Promise.reject(error);
    }
);

export async function apiGet(path) {
    const res = await api.get(path);
    return res.data;
}

export async function apiSend(path, method, body) {
    const res = await api({ method, url: path, data: body });
    return res.data;
}

// Auth endpoints
export async function apiLogin(username, password) {
    const res = await api.post("/api/auth/login", { username, password });
    return res.data; // { username, token }
}

export async function apiLogout() {
    // For JWT, "logout" is client-side; we can still hit backend if you want logs
    const res = await api.post("/api/auth/logout");
    return res.data;
}

export async function apiMe() {
    const res = await api.get("/api/auth/me");
    return res.data; // { username } or 401
}

export default api;

