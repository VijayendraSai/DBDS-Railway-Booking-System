import React, { useState } from "react";
import axios from "axios";
import { Form, Button, Spinner, Alert, Container } from "react-bootstrap";

function CancelReservation() {
  const [reservationId, setReservationId] = useState(""); // Track reservation ID
  const [message, setMessage] = useState(""); // Store response message
  const [isLoading, setIsLoading] = useState(false); // Track loading state
  const [error, setError] = useState(null); // Track errors

  // Handle cancel reservation action
  const handleCancelReservation = async () => {
    setIsLoading(true);
    setMessage(""); // Reset message on new request
    setError(null); // Reset error state

    try {
      const customerId = sessionStorage.getItem("customerId"); // Get customerId from sessionStorage

      if (!customerId) {
        setError("You must be logged in to cancel a reservation.");
        setIsLoading(false);
        return;
      }

      // Make API call to cancel reservation
      const response = await axios.delete(
        `http://localhost:8080/reservations/cancel/${reservationId}`,
        {
          params: { customerId: customerId }, // Pass customerId as a query param
        }
      );

      setMessage(response.data); // Set success message from the API
    } catch (err) {
      setError("Failed to cancel reservation. Please try again.");
    } finally {
      setIsLoading(false); // Stop loading after request
    }
  };

  return (
    <Container className="mt-5">
      <h2 className="text-center mb-4">Cancel Reservation</h2>
      <Form>
        {/* Input field for reservationId */}
        <Form.Group className="mb-3">
          <Form.Label>Reservation ID</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter reservation ID"
            value={reservationId}
            onChange={(e) => setReservationId(e.target.value)}
            required
          />
        </Form.Group>

        {/* Cancel Button */}
        <div className="d-grid">
          <Button
            variant="danger"
            onClick={handleCancelReservation}
            disabled={isLoading || !reservationId}
          >
            {isLoading ? (
              <>
                <Spinner
                  as="span"
                  animation="border"
                  size="sm"
                  role="status"
                  aria-hidden="true"
                />
                Cancelling...
              </>
            ) : (
              "Cancel Reservation"
            )}
          </Button>
        </div>
      </Form>

      {/* Display message based on response or error */}
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

export default CancelReservation;
