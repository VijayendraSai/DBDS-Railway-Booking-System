import React, { useState } from "react";
import axios from "axios";
import { Container, Form, Button, Alert } from "react-bootstrap";

function DeleteRepresentative() {
  const [representativeId, setRepresentativeId] = useState("");
  const [message, setMessage] = useState("");
  const [error, setError] = useState(null);

  const handleDelete = async () => {
    setMessage("");
    setError(null);

    if (!representativeId) {
      setError("Please enter a valid representative ID.");
      return;
    }

    try {
      const response = await axios.delete(
        `http://localhost:8080/admin/representatives/${representativeId}`
      );
      setMessage(response.data); // Response message from the server
    } catch (error) {
      if (error.response) {
        setError(`Error: ${error.response.data}`);
      } else {
        setError("An unexpected error occurred.");
      }
    }
  };

  return (
    <Container className="mt-5">
      <h2 className="text-center mb-4">Delete Representative</h2>
      <Form>
        {/* Input field for representative ID */}
        <Form.Group className="mb-3">
          <Form.Label>Representative ID</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter Representative ID"
            value={representativeId}
            onChange={(e) => setRepresentativeId(e.target.value)}
          />
        </Form.Group>

        {/* Delete Button */}
        <div className="d-grid">
          <Button variant="danger" onClick={handleDelete}>
            Delete Representative
          </Button>
        </div>
      </Form>

      {/* Display success or error message */}
      {message && (
        <Alert className="mt-3" variant="success">
          {message}
        </Alert>
      )}
      {error && (
        <Alert className="mt-3" variant="danger">
          {error}
        </Alert>
      )}
    </Container>
  );
}

export default DeleteRepresentative;
