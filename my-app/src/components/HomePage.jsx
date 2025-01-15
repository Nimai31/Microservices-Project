import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom"; // Import useNavigate for navigation
import Login from "./Login";
import Register from "./Register";
import "./HomePage.css";

const HomePage = () => {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [isRegistering, setIsRegistering] = useState(false);
  const [animationClass, setAnimationClass] = useState("");
  const navigate = useNavigate(); // Initialize useNavigate

  useEffect(() => {
    // Trigger animation after the component mounts
    setAnimationClass("drop-in");

    // Check if the user is already logged in (if their email exists in session storage)
    const userEmail = sessionStorage.getItem("userEmail");
    if (userEmail) {
      setIsLoggedIn(true);
      navigate("/tasks"); // Redirect to tasks page if already logged in
    }
  }, [navigate]);

  const handleLogin = (email) => {
    sessionStorage.setItem("userEmail", email); // Store email in session storage
    setIsLoggedIn(true); // Update logged-in status
    navigate("/tasks"); // Navigate to the tasks page after login
  };

  const handleRegister = (email) => {
    setIsRegistering(true);
    sessionStorage.setItem("userEmail", email);
  };

  const handleLogout = () => {
    sessionStorage.removeItem("userEmail"); // Remove user email from session storage
    setIsLoggedIn(false);
  };

  return (
    <div>

      <h1 className="title">Course Registration Portal</h1>

      <div className={`homepage-container ${animationClass}`}>
        <div className="left-side">
          <img src="/login1.jpeg" alt="Course Registration" className="task-image" />
        </div>

        <div className="right-side">
          {!isLoggedIn ? (
            !isRegistering ? (
              <Login onLogin={handleLogin} />
            ) : (
              <Register onRegister={handleLogin} />
            )
          ) : (
            // If the user is logged in, redirect to the tasks page
            navigate("/tasks")
          )}

          {!isRegistering && !isLoggedIn && (
            <div>
              <h3>
                Don't have an account?{" "}
                <button onClick={handleRegister}>Register</button>
              </h3>
            </div>
          )}
        </div>
      </div>
    </div>
  );
};

export default HomePage;
