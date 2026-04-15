import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login() {
  const navigate = useNavigate();

  const [user, setUser] = useState({
    username: "",
    password: ""
  });

  const handleChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const handleLogin = (e) => {
    e.preventDefault(); // 🔥 prevent reload

    if (!user.username || !user.password) {
      alert("Please fill all fields");
      return;
    }

    axios.post("http://localhost:9090/api/auth/login", user)
      .then(res => {
        if (res.data) {
          localStorage.setItem("user", JSON.stringify(res.data));
          navigate("/dashboard"); // ✅ React routing
        } else {
          alert("Invalid Credentials ❌");
        }
      })
      .catch(() => {
        alert("Server Error ❌");
      });
  };

  return (
    <div className="container mt-5">
      <div className="card p-4 shadow col-md-4 mx-auto">
        <h3 className="text-center mb-3">Login</h3>

        <form onSubmit={handleLogin}>
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

          <button className="btn btn-success w-100">
            Login
          </button>
        </form>

        {/* 🔗 Register Link */}
        <p className="text-center mt-3">
          New user?{" "}
          <span
            style={{ color: "blue", cursor: "pointer" }}
            onClick={() => navigate("/register")}
          >
            Register here
          </span>
        </p>
      </div>
    </div>
  );
}

export default Login;
