import React, { useState } from "react";
import axios from "axios";

function SearchQuestionService() {
  const [keyword, setKeyword] = useState(""); // State to hold the keyword input
  const [questions, setQuestions] = useState([]); // State to hold the fetched questions
  const [error, setError] = useState(null); // State for error handling

  // Handle search input change
  const handleKeywordChange = (event) => {
    setKeyword(event.target.value);
  };

  // Handle search action
  const handleSearch = async () => {
    setError(null); // Reset error
    if (!keyword) {
      setError("Please enter a keyword to search.");
      return;
    }

    try {
      const response = await axios.get(
        `http://localhost:8080/customer-service/search-by-keyword?keyword=${keyword}`
      );
      if (response.data.length === 0) {
        setError("No questions found for the given keyword.");
        setQuestions([]);
      } else {
        setQuestions(response.data);
      }
    } catch (err) {
      setError("Failed to search questions. Please try again.");
    }
  };

  return (
    <div className="container py-5">
      <h1 className="text-center mb-4">Search Questions</h1>

      {/* Input field and Search button */}
      <div className="d-flex justify-content-center mb-4">
        <input
          type="text"
          value={keyword}
          onChange={handleKeywordChange}
          placeholder="Enter a keyword to search"
          className="form-control w-50"
        />
        <button className="btn btn-primary mx-2" onClick={handleSearch}>
          Search
        </button>
      </div>

      {/* Error message */}
      {error && <p className="text-danger text-center">{error}</p>}

      {/* Display search results */}
      {questions.length > 0 && (
        <div>
          <h2>Search Results</h2>
          <table className="table table-striped table-bordered mt-3">
            <thead>
              <tr>
                <th>Question</th>
                <th>Answer</th>
              </tr>
            </thead>
            <tbody>
              {questions.map((q, index) => (
                <tr key={index}>
                  <td>{q.question}</td>
                  <td>{q.answer || "No answer available"}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

export default SearchQuestionService;
