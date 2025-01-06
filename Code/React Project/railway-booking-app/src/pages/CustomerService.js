import React, { useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

function CustomerService() {
  const [allQuestions, setAllQuestions] = useState([]);
  const [customerQuestions, setCustomerQuestions] = useState([]);
  const [error, setError] = useState(null);

  // Fetch all answered questions
  const handleBrowseQuestions = async () => {
    setError(null); // Reset error
    try {
      const response = await axios.get(
        "http://localhost:8080/customer-service/all-questions"
      );
      setAllQuestions(response.data);
    } catch (err) {
      setError("Failed to load questions. Please try again.");
    }
  };

  // Fetch customer-specific questions
  const handleMyQuestions = async () => {
    setError(null); // Reset error
    try {
      const customerId = sessionStorage.getItem("customerId");
      if (!customerId) {
        setError("You must be logged in to view your questions.");
        return;
      }

      const response = await axios.get(
        `http://localhost:8080/customer-service/customer-questions?customerId=${customerId}`
      );
      setCustomerQuestions(response.data);
    } catch (err) {
      setError("Failed to load your questions. Please try again.");
    }
  };

  return (
    <div className="container py-5">
      <h1 className="text-center mb-4">Customer Service</h1>

      {/* Buttons with Bootstrap styling */}
      <div className="d-flex justify-content-center mb-4">
        <button
          className="btn btn-primary mx-2"
          onClick={handleBrowseQuestions}
        >
          Browse Questions
        </button>
        <Link to="/search-question">
          <button className="btn btn-secondary mx-2">Search Question</button>
        </Link>
        <Link to="/send-question">
          <button className="btn btn-success mx-2">Send Question</button>
        </Link>
        <button className="btn btn-info mx-2" onClick={handleMyQuestions}>
          My Questions
        </button>
      </div>

      {/* Error Message */}
      {error && <p className="text-danger">{error}</p>}

      {/* Display All Questions */}
      {allQuestions.length > 0 && (
        <div>
          <h2>All Questions and Answers</h2>
          <table className="table table-striped table-bordered mt-3">
            <thead>
              <tr>
                <th>Question</th>
                <th>Answer</th>
              </tr>
            </thead>
            <tbody>
              {allQuestions.map((q, index) => (
                <tr key={index}>
                  <td>{q.question}</td>
                  <td>{q.answer}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      {/* Display Customer's Questions */}
      {customerQuestions.length > 0 && (
        <div>
          <h2>My Questions</h2>
          <table className="table table-striped table-bordered mt-3">
            <thead>
              <tr>
                <th>Question</th>
                <th>Answer</th>
                <th>Question Timestamp</th>
                <th>Answer Timestamp</th>
                <th>Answered</th>
              </tr>
            </thead>
            <tbody>
              {customerQuestions.map((q, index) => (
                <tr key={index}>
                  <td>{q.question}</td>
                  <td>{q.answer || "Not answered yet"}</td>
                  <td>{q.questionTimestamp}</td>
                  <td>{q.answerTimestamp || "N/A"}</td>
                  <td>{q.answered ? "Yes" : "No"}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

export default CustomerService;
