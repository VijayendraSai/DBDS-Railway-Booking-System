import React, { useState } from "react";
import axios from "axios";

function SendQuestionService() {
  const [question, setQuestion] = useState(""); // State to hold the question input
  const [error, setError] = useState(null); // State for error handling
  const [successMessage, setSuccessMessage] = useState(null); // State for success message

  // Handle question input change
  const handleQuestionChange = (event) => {
    setQuestion(event.target.value);
  };

  // Handle submit action
  const handleSubmit = async (event) => {
    event.preventDefault();
    setError(null); // Reset error
    setSuccessMessage(null); // Reset success message

    if (!question) {
      setError("Please enter a question before submitting.");
      return;
    }

    try {
      // Assuming the customerId is stored in sessionStorage (replace with your method of fetching customerId)
      const customerId = sessionStorage.getItem("customerId");

      if (!customerId) {
        setError("You must be logged in to submit a question.");
        return;
      }

      const requestDTO = {
        customerId: parseInt(customerId),
        question: question,
      };

      const response = await axios.post(
        "http://localhost:8080/customer-service/send-question",
        requestDTO
      );

      setSuccessMessage(response.data); // Set success message upon successful submission
      setQuestion(""); // Clear the input field
    } catch (err) {
      setError("Failed to send your question. Please try again.");
    }
  };

  return (
    <div className="container py-5">
      <h1 className="text-center mb-4">Send a Question to Customer Service</h1>

      {/* Display success or error messages */}
      {successMessage && (
        <p className="alert alert-success text-center">{successMessage}</p>
      )}
      {error && <p className="alert alert-danger text-center">{error}</p>}

      {/* Question submission form */}
      <form onSubmit={handleSubmit} className="mt-4">
        <div className="mb-3">
          <textarea
            value={question}
            onChange={handleQuestionChange}
            placeholder="Enter your question here"
            rows="5"
            className="form-control"
          ></textarea>
        </div>

        <div className="text-center">
          <button type="submit" className="btn btn-primary">
            Submit Question
          </button>
        </div>
      </form>
    </div>
  );
}

export default SendQuestionService;
