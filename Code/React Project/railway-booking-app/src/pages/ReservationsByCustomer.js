import React, { useState } from "react";
import axios from "axios";

function ReservationsByCustomer() {
  const [email, setEmail] = useState("");
  const [reservations, setReservations] = useState([]);
  const [error, setError] = useState("");

  const fetchReservations = async () => {
    if (!email) {
      setError("Please enter an email address.");
      return;
    }

    try {
      const response = await axios.get(
        `http://localhost:8080/admin/reservations-by-customer?email=${email}`
      );
      setReservations(response.data);
      setError("");
    } catch (err) {
      setError("Failed to fetch reservations. Please try again.");
    }
  };

  return (
    <div className="container mt-5">
      <h1 className="text-center">Reservations by Customer</h1>
      <div className="mb-3">
        <input
          type="email"
          className="form-control"
          placeholder="Enter Customer Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
        <button className="btn btn-primary mt-2" onClick={fetchReservations}>
          Fetch Reservations
        </button>
      </div>
      {error && <div className="alert alert-danger">{error}</div>}
      {reservations.length > 0 ? (
        <table className="table table-striped table-bordered mt-3">
          <thead className="thead-dark">
            <tr>
              <th>Reservation ID</th>
              <th>Reservation Date</th>
              <th>Train Name</th>
              <th>Passenger Name</th>
              <th>Origin Station</th>
              <th>Destination Station</th>
              <th>Total Fare</th>
            </tr>
          </thead>
          <tbody>
            {reservations.map((reservation, index) => (
              <tr key={index}>
                <td>{reservation.reservationId}</td>
                <td>{reservation.reservationDate}</td>
                <td>{reservation.trainName}</td>
                <td>{reservation.passengerName}</td>
                <td>{reservation.originStationName}</td>
                <td>{reservation.destinationStationName}</td>
                <td>${reservation.totalFare.toFixed(2)}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        !error && <p className="text-center">No reservations found.</p>
      )}
    </div>
  );
}

export default ReservationsByCustomer;
