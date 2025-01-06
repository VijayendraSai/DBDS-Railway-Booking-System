import React, { useState, useEffect } from "react";
import axios from "axios";

function MyReservations() {
  const [reservations, setReservations] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  // Fetch reservations based on type
  const fetchReservations = async (type) => {
    setIsLoading(true);
    setError(null);

    try {
      const customerId = sessionStorage.getItem("customerId"); // Get customerId from sessionStorage
      const endpoint =
        type === "current" ? "/reservations/current" : "/reservations/past";

      const response = await axios.get(`http://localhost:8080${endpoint}`, {
        params: { customerId },
      });

      setReservations(response.data);
    } catch (err) {
      setError("Failed to fetch reservations.");
    } finally {
      setIsLoading(false);
    }
  };

  // Render reservation details in table rows
  const renderReservations = () => {
    return reservations.map((reservation) => (
      <tr key={reservation.reservationId}>
        <td>{reservation.reservationId}</td>
        <td>{reservation.train.trainId}</td>
        <td>{reservation.trainName}</td>
        <td>{reservation.passengerName}</td>
        <td>{reservation.passengerType}</td>
        <td>{reservation.reservationDate}</td>
        <td>{reservation.originStationName}</td>
        <td>{reservation.destinationStationName}</td>
        <td>${reservation.totalFare}</td>
        <td>{reservation.tripType}</td>
      </tr>
    ));
  };

  return (
    <div className="container py-5">
      <h1 className="text-center mb-4">My Reservations</h1>
      <div className="text-center mb-4">
        <button
          onClick={() => fetchReservations("current")}
          className="btn btn-primary mx-2"
        >
          Current Reservations
        </button>
        <button
          onClick={() => fetchReservations("past")}
          className="btn btn-secondary mx-2"
        >
          Past Reservations
        </button>
      </div>

      {/* Display loading, error, and reservation content */}
      {isLoading && <p className="text-center">Loading reservations...</p>}
      {error && <p className="alert alert-danger text-center">{error}</p>}

      {reservations.length > 0 ? (
        <table className="table table-bordered table-striped">
          <thead className="table-dark">
            <tr>
              <th>Reservation ID</th>
              <th>Train ID</th>
              <th>Train Name</th>
              <th>Passenger</th>
              <th>Passenger Type</th>
              <th>Reservation Date</th>
              <th>Origin</th>
              <th>Destination</th>
              <th>Fare</th>
              <th>Trip Type</th>
            </tr>
          </thead>
          <tbody>{renderReservations()}</tbody>
        </table>
      ) : (
        !isLoading && <p className="text-center">No reservations to display.</p>
      )}
    </div>
  );
}

export default MyReservations;
