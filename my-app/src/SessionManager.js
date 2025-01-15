// src/SessionManager.js
const SessionManager = {
  setUserEmail: (email) => localStorage.setItem("userEmail", email),
  getUserEmail: () => localStorage.getItem("userEmail"),
  clearSession: () => localStorage.removeItem("userEmail"),
};

export default SessionManager;
