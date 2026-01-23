// session.js

const SESSION_KEY = "currentUser";

export function saveUserSession(user) {
    sessionStorage.setItem(SESSION_KEY, JSON.stringify(user));
}

export function getUserSession() {
    const data = sessionStorage.getItem(SESSION_KEY);
    return data ? JSON.parse(data) : null;
}

export function clearUserSession() {
    sessionStorage.removeItem(SESSION_KEY);
}

export function isLoggedIn() {
    return getUserSession() !== null;
}