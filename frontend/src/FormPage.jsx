import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./FormPage.css";
import { FaLock, FaUser } from "react-icons/fa";
import { TOKEN_KEY, apiLogin, apiRegister } from "./components/authApi";

const FormPage = () => {
  const [isLoginMode, setIsLoginMode] = useState(true);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [status, setStatus] = useState(null);
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setStatus(null);

    try {
      if (isLoginMode) {
        const response = await apiLogin(username, password);
        localStorage.setItem(TOKEN_KEY, response.token);
        localStorage.setItem("username", username);

        const QUESTIONS_KEY = `vibeQuestions_${username}`;
        const ANSWERS_KEY = `vibeAnswers_${username}`;
        const RESULT_KEY = `vibeResult_${username}`;

        localStorage.removeItem(QUESTIONS_KEY);
        localStorage.removeItem(ANSWERS_KEY);
        localStorage.removeItem(RESULT_KEY);

        setStatus("Login successful! Redirecting...");
        setTimeout(() => {
          navigate("/home");
        }, 1000);
      } else {
        if (password !== confirmPassword) {
          setStatus("Passwords do not match!");
          setLoading(false);
          return;
        }

        await apiRegister(username, password);

        setStatus("Signup successful! Please log in.");
        setIsLoginMode(true);
        setPassword("");
        setConfirmPassword("");
      }
    } catch (error) {
      setStatus(
        "Error: " + (error?.response?.data?.error || "Something went wrong")
      );
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="formpage-container">
      <div className="form-box">
        <h2>{isLoginMode ? "Login" : "Sign Up"}</h2>

        <div className="mode-toggle">
          <button
            className={isLoginMode ? "active-tab" : ""}
            onClick={() => setIsLoginMode(true)}
          >
            Login
          </button>

          <button
            className={!isLoginMode ? "active-tab" : ""}
            onClick={() => setIsLoginMode(false)}
          >
            SignUp
          </button>
        </div>

        <form onSubmit={handleSubmit}>
          <div className="input-group">
            <input
              type="text"
              placeholder="Username"
              required
              value={username}
              onChange={(e) => setUsername(e.target.value)}
            />
            <FaUser className="icon" />
          </div>

          <div className="input-group">
            <input
              type="password"
              placeholder="Password"
              required
              value={password}
              onChange={(e) => setPassword(e.target.value)}
            />
            <FaLock className="icon" />
          </div>

          {!isLoginMode && (
            <div className="input-group">
              <input
                type="password"
                placeholder="Confirm Password"
                required
                value={confirmPassword}
                onChange={(e) => setConfirmPassword(e.target.value)}
              />
            </div>
          )}

          {isLoginMode && (
            <div className="remember-forgot">
              <label>
                <input type="checkbox" /> Remember me
              </label>
              <a href="#">Forget Password</a>
            </div>
          )}

          <button
            type="submit"
            className="submit-button"
            disabled={loading}
          >
            {loading
              ? isLoginMode
                ? "Logging in..."
                : "Signing up..."
              : isLoginMode
              ? "Login"
              : "Sign up"}
          </button>

          {status && <div className="status-message">{status}</div>}

          <p className="signup-link">
            {isLoginMode
              ? "Don't have an account"
              : "Already have an account"}
            <a
              href="#"
              onClick={(e) => {
                e.preventDefault();
                setIsLoginMode(!isLoginMode);
              }}
            >
              {isLoginMode ? " Signup now" : " Login"}
            </a>
          </p>
        </form>
      </div>
    </div>
  );
};

export default FormPage;
