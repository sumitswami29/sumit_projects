import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Register() {
  const navigate = useNavigate();

  const [user, setUser] = useState({
    username: "",
    password: "",
    confirmPassword: ""
  });

  const handleChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // ✅ Validation
    if (!user.username || !user.password || !user.confirmPassword) {
      alert("All fields are required");
      return;
    }

    if (user.password !== user.confirmPassword) {
      alert("Passwords do not match");
      return;
    }

    axios.post("http://localhost:9090/api/auth/register", {
      username: user.username,
      password: user.password
    })
      .then(() => {
        alert("Registered Successfully ✅");
        navigate("/"); // redirect to login
      })
      .catch(() => {
        alert("Registration Failed ❌");
      });
  };

  return (
    <div className="container mt-5">
      <div className="card p-4 shadow col-md-4 mx-auto">
        <h3 className="text-center mb-3">Register</h3>

        <form onSubmit={handleSubmit}>
          <input
            name="username"
            className="form-control mb-3"
            placeholder="Username"
            onChange={handleChange}
          />

          <input
            name="password"
            type="password"
            className="form-control mb-3"
            placeholder="Password"
            onChange={handleChange}
          />

          <input
            name="confirmPassword"
            type="password"
            className="form-control mb-3"
            placeholder="Confirm Password"
            onChange={handleChange}
          />

          <button className="btn btn-primary w-100">
            Register
          </button>
        </form>

        {/* 🔗 Back to Login */}
        <p className="text-center mt-3">
          Already have an account?{" "}
          <span
            style={{ color: "blue", cursor: "pointer" }}
            onClick={() => navigate("/")}
          >
            Login here
          </span>
        </p>
      </div>
    </div>
  );
}

export default Register;
