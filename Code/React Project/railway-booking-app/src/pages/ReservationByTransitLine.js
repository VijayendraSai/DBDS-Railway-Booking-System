import React, { useEffect, useState } from "react";
import axios from "axios";

function ReservationByTransitLine() {
  const [transitLineNames, setTransitLineNames] = useState([]); // Store available transit line names
  const [transitLineName, setTransitLineName] = useState(""); // Selected transit line name
  const [reservations, setReservations] = useState([]);
  const [error, setError] = useState("");

  // Fetch available transit line names
  useEffect(() => {
    const fetchTransitLineNames = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/transitLine/names"
        );
        setTransitLineNames(response.data); // Populate transit line names
      } catch (err) {
        setError("Failed to fetch transit line names.");
      }
    };

    fetchTransitLineNames();
  }, []);

  // Fetch reservations for the selected transit line
  const fetchReservations = async () => {
    if (!transitLineName) {
      setError("Please select a transit line name.");
      return;
    }

    try {
      const response = await axios.get(
        `http://localhost:8080/admin/reservations-by-transit-line?transitLineName=${transitLineName}`
      );
      setReservations(response.data);
      setError(""); // Clear error on successful fetch
    } catch (err) {
      setError("Failed to fetch reservations. Please try again.");
    }
  };

  return (
    <div className="container mt-5">
      <h1 className="text-center">Reservations by Transit Line</h1>

      {/* Dropdown for selecting transit line name */}
      <div className="mb-3">
        <label htmlFor="transitLineSelect" className="form-label">
          Select Transit Line
        </label>
        <select
          id="transitLineSelect"
          className="form-select"
          value={transitLineName}
          onChange={(e) => setTransitLineName(e.target.value)}
        >
          <option value="">-- Select a Transit Line --</option>
          {transitLineNames.map((name, index) => (
            <option key={index} value={name}>
              {name}
            </option>
          ))}
        </select>
        <button className="btn btn-primary mt-3" onClick={fetchReservations}>
          Fetch Reservations
        </button>
      </div>

      {/* Error Message */}
      {error && <div className="alert alert-danger">{error}</div>}

      {/* Reservations Table */}
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

export default ReservationByTransitLine;
