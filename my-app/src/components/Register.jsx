import React, { useState } from "react";
import axios from "axios";
import "./HomePage.css"

const Register = ({ onRegister }) => {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [age, setAge] = useState("");
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    // Check if passwords match
    if (password !== confirmPassword) {
      setError("Passwords do not match.");
      return;
    }

    try {
      // Send the registration request
      const response = await axios.post("http://localhost:8083/users/register", {
        username,
        email,
        password,
        age,
      });

      console.log("User registered:", response.data);

      // Store the email in sessionStorage after successful registration
      sessionStorage.setItem("userEmail", email);

      // Notify the parent component that registration is successful
      onRegister(email);
    } catch (error) {
      setError("Registration failed. Please try again.");
      console.error("Registration failed:", error.response?.data || error.message);
    }
  };

  return (
    <div className="auth-container">
    <h2>Register</h2>
    {error && <p className="error">{error}</p>}
    <form onSubmit={handleSubmit} className="auth-form">
      <input
        type="text"
        placeholder="Username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
        required
      />
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
      <input
        type="password"
        placeholder="Confirm Password"
        value={confirmPassword}
        onChange={(e) => setConfirmPassword(e.target.value)}
        required
      />
      <input
        type="number"
        placeholder="Semester"
        value={age}
        onChange={(e) => setAge(e.target.value)}
        required
      />
      <button type="submit">Register</button>
    </form>
  </div>
  
  );
};

export default Register;
