import React, { useState } from "react";
import axios from "axios";
import "./Auth.css"

const Login = ({ onLogin }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    try {
      // Make the login request
      const response = await axios.post(
        `http://localhost:8083/users/login?username=${email}&password=${password}`
      );

      // Store the email in session storage after successful login
      sessionStorage.setItem("userEmail", email);

      console.log("User logged in:", email);

      // Notify the parent component that login is successful
      onLogin(email);
    } catch (error) {
      setError("Invalid credentials. Please try again.");
      console.error("Login failed:", error.response?.data || error.message);
    }
  };

  return (
    <div className="auth-container">
  <h2>Login</h2>
  {error && <p className="error">{error}</p>}
  <form onSubmit={handleSubmit} className="auth-form">
    <input
      type="email"
      placeholder="Email"
      value={email}
      onChange={(e) => setEmail(e.target.value)}
      required
    />
    <input
      type="password"
      placeholder="Password"
      value={password}
      onChange={(e) => setPassword(e.target.value)}
      required
    />
    <button type="submit">Login</button>
  </form>
</div>
  );
};

export default Login;
