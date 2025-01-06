import React, { useState, useEffect } from "react";
import axios from "axios";

function ReplyService() {
  const [unansweredQuestions, setUnansweredQuestions] = useState([]);
  const [errorMessage, setErrorMessage] = useState("");
  const [isModalOpen, setIsModalOpen] = useState(false); // To toggle modal visibility
  const [currentQuestionId, setCurrentQuestionId] = useState(null); // Store current question ID
  const [answer, setAnswer] = useState(""); // Store inputted answer

  // Fetch unanswered questions from the backend
  useEffect(() => {
    axios
      .get("http://localhost:8080/customer-service/unanswered-questions")
      .then((response) => {
        if (response.status === 200) {
          setUnansweredQuestions(response.data);
        }
      })
      .catch((error) => {
        console.error("Error fetching unanswered questions:", error);
        setErrorMessage("Failed to load unanswered questions.");
      });
  }, []);

  // Handle the modal close
  const closeModal = () => {
    setIsModalOpen(false);
    setAnswer(""); // Clear answer input when modal closes
  };

  // Handle the modal open with the current question
  const openModal = (questionId) => {
    setCurrentQuestionId(questionId);
    setIsModalOpen(true);
  };

  // Handle the answer submission
  const handleSubmitAnswer = () => {
    if (answer.trim() === "") {
      alert("Please provide an answer.");
      return;
    }

    // Create the request body in the format expected by the backend
    const replyToQuestionDTO = {
      serviceId: currentQuestionId,
      answer: answer,
    };

    // Submit the answer to the backend using PUT request
    axios
      .put("http://localhost:8080/customer-service/reply", replyToQuestionDTO)
      .then((response) => {
        alert("Answer submitted successfully!");
        closeModal();
        // Refresh unanswered questions after submitting
        setUnansweredQuestions((prevState) =>
          prevState.filter(
            (question) => question.serviceId !== currentQuestionId
          )
        );
      })
      .catch((error) => {
        console.error("Error submitting the answer:", error);
        alert("Failed to submit the answer.");
      });
  };

  return (
    <div className="container mt-4">
      <h1 className="text-center mb-4">Unanswered Questions</h1>
      {errorMessage && (
        <div className="alert alert-danger mb-4">{errorMessage}</div>
      )}

      <table className="table table-striped table-bordered">
        <thead className="thead-dark">
          <tr>
            <th>Service ID</th>
            <th>Customer ID</th>
            <th>Question</th>
            <th>Timestamp</th>
            <th>Answer</th>
          </tr>
        </thead>
        <tbody>
          {unansweredQuestions.map((question) => (
            <tr key={question.serviceId}>
              <td>{question.serviceId}</td>
              <td>{question.customerId}</td>
              <td>{question.question}</td>
              <td>{question.questionTimestamp}</td>
              <td>
                <button
                  className="btn btn-primary"
                  onClick={() => openModal(question.serviceId)}
                >
                  Answer
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Modal for answering the question */}
      {isModalOpen && (
        <div
          className="modal fade show"
          style={{ display: "block" }}
          tabIndex="-1"
          role="dialog"
        >
          <div className="modal-dialog modal-dialog-centered" role="document">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title">Answer the Question</h5>
                <button
                  type="button"
                  className="close"
                  data-dismiss="modal"
                  aria-label="Close"
                  onClick={closeModal}
                >
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div className="modal-body">
                <textarea
                  className="form-control"
                  rows="4"
                  placeholder="Enter your answer"
                  value={answer}
                  onChange={(e) => setAnswer(e.target.value)}
                />
              </div>
              <div className="modal-footer">
                <button
                  type="button"
                  className="btn btn-secondary"
                  onClick={closeModal}
                >
                  Close
                </button>
                <button
                  type="button"
                  className="btn btn-primary"
                  onClick={handleSubmitAnswer}
                >
                  Submit Answer
                </button>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default ReplyService;
