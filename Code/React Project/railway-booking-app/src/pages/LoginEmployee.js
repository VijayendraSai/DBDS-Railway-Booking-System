import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

function LoginEmployee() {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });

  const [errorMessage, setErrorMessage] = useState("");
  const navigate = useNavigate(); // Hook for programmatic navigation

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  // Function to log the employee in and store employee data in sessionStorage
  const loginEmployee = async (username, password) => {
    try {
      const response = await axios.post(
        "http://localhost:8080/employees/login",
        { username, password }
      );

      if (response.status === 200) {
        const employeeData = response.data; // Get employee data from the response

        // Store employee data in sessionStorage
        sessionStorage.setItem("empId", employeeData.empId);
        sessionStorage.setItem("email", employeeData.email);
        sessionStorage.setItem("employeeFirstName", employeeData.firstName);
        sessionStorage.setItem("employeeLastName", employeeData.lastName);
        sessionStorage.setItem("role", employeeData.role);

        console.log("Login successful:", response.data);
        alert("Login successful! Welcome, " + employeeData.firstName);

        // Redirect to home page after successful login
        if (sessionStorage.getItem("role") === "ADMIN") {
          navigate("/home-admin");
        } else {
          navigate("/home-representative");
        }
      }
    } catch (error) {
      console.error("Login failed:", error);
      setErrorMessage(
        error.response?.data.message || "Invalid username or password"
      );
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const { username, password } = formData;

    // Call the loginEmployee function
    loginEmployee(username, password);
  };

  return (
    <div
      className="container d-flex justify-content-center align-items-center mt-5"
      style={{ minHeight: "100vh" }}
    >
      <div className="col-md-4">
        <h2 className="text-center mb-4">Employee Login</h2>
        {errorMessage && (
          <div className="alert alert-danger">{errorMessage}</div>
        )}
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="username">Username</label>
            <input
              type="text"
              className="form-control"
              id="username"
              name="username"
              value={formData.username}
              onChange={handleChange}
              required
            />
          </div>

          <div className="form-group">
            <label htmlFor="password">Password</label>
            <input
              type="password"
              className="form-control"
              id="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              required
            />
          </div>

          <button type="submit" className="btn btn-primary mt-3 w-100">
            Login
          </button>
        </form>
      </div>
    </div>
  );
}

export default LoginEmployee;
