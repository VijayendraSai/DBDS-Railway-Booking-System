import React, { useState } from "react";
import axios from "axios";

function AddRepresentative() {
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    username: "",
    password: "",
    ssn: "",
  });

  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  // Handle form input changes
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  // Handle form submission
  const handleSubmit = (e) => {
    e.preventDefault();

    axios
      .post("http://localhost:8080/admin/representatives", formData)
      .then((response) => {
        setSuccessMessage("Representative added successfully!");
        setErrorMessage("");
        setFormData({
          firstName: "",
          lastName: "",
          email: "",
          username: "",
          password: "",
          ssn: "",
        });
      })
      .catch((error) => {
        console.error("Error adding representative:", error);
        setErrorMessage(
          error.response?.data?.message || "Failed to add representative."
        );
        setSuccessMessage("");
      });
  };

  return (
    <div className="container mt-4">
      <h1 className="text-center mb-4">Add Customer Representative</h1>
      <form
        onSubmit={handleSubmit}
        className="mx-auto"
        style={{ maxWidth: "400px" }}
      >
        <div className="mb-3">
          <label htmlFor="firstName" className="form-label">
            First Name
          </label>
          <input
            type="text"
            className="form-control"
            id="firstName"
            name="firstName"
            value={formData.firstName}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="lastName" className="form-label">
            Last Name
          </label>
          <input
            type="text"
            className="form-control"
            id="lastName"
            name="lastName"
            value={formData.lastName}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="email" className="form-label">
            Email
          </label>
          <input
            type="email"
            className="form-control"
            id="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="username" className="form-label">
            Username
          </label>
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
        <div className="mb-3">
          <label htmlFor="password" className="form-label">
            Password
          </label>
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
        <div className="mb-3">
          <label htmlFor="ssn" className="form-label">
            SSN
          </label>
          <input
            type="text"
            className="form-control"
            id="ssn"
            name="ssn"
            value={formData.ssn}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary w-100">
          Add Representative
        </button>
      </form>

      {successMessage && (
        <div className="alert alert-success mt-3">{successMessage}</div>
      )}

      {errorMessage && (
        <div className="alert alert-danger mt-3">{errorMessage}</div>
      )}
    </div>
  );
}

export default AddRepresentative;
