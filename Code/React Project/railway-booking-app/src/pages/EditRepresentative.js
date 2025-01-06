import React, { useState } from "react";
import axios from "axios";

function EditRepresentative() {
  const [id, setId] = useState(""); // ID of the representative to edit
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

  // Handle input changes for the form fields
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  // Handle the ID field change
  const handleIdChange = (e) => {
    setId(e.target.value);
  };

  // Submit the form to update the representative
  const handleSubmit = (e) => {
    e.preventDefault();

    if (!id.trim()) {
      alert("Please enter the ID of the representative to edit.");
      return;
    }

    axios
      .put(`http://localhost:8080/admin/representatives/${id}`, formData)
      .then((response) => {
        setSuccessMessage("Representative updated successfully!");
        setErrorMessage("");
      })
      .catch((error) => {
        console.error("Error updating representative:", error);
        setErrorMessage("Failed to update representative. Please try again.");
        setSuccessMessage("");
      });
  };

  return (
    <div className="container mt-4">
      <h1 className="mb-4 text-center">Edit Customer Representative</h1>

      {/* Form to edit representative details */}
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="id" className="form-label">
            Representative ID
          </label>
          <input
            type="text"
            className="form-control"
            id="id"
            value={id}
            onChange={handleIdChange}
            placeholder="Enter Representative ID"
          />
        </div>

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
            onChange={handleInputChange}
            placeholder="Enter First Name"
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
            onChange={handleInputChange}
            placeholder="Enter Last Name"
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
            onChange={handleInputChange}
            placeholder="Enter Email"
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
            onChange={handleInputChange}
            placeholder="Enter Username"
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
            onChange={handleInputChange}
            placeholder="Enter Password"
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
            onChange={handleInputChange}
            placeholder="Enter SSN"
          />
        </div>

        <button type="submit" className="btn btn-primary">
          Update Representative
        </button>
      </form>

      {/* Success and Error Messages */}
      {successMessage && (
        <div className="alert alert-success mt-3">{successMessage}</div>
      )}
      {errorMessage && (
        <div className="alert alert-danger mt-3">{errorMessage}</div>
      )}
    </div>
  );
}

export default EditRepresentative;
