import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";

function TrainBooking() {
  const location = useLocation();
  const { state } = location;
  const {
    trainName,
    trainId,
    transitLineName,
    originArrivalTime,
    destinationArrivalTime,
    originStationName,
    destinationStationName,
    calculatedFare,
  } = state || {};

  const [tripType, setTripType] = useState("");
  const [passengerType, setPassengerType] = useState("");
  const [reservationDate, setReservationDate] = useState("");
  const [passengerName, setPassengerName] = useState("");
  const [totalFare, setTotalFare] = useState("");
  const [reservationStatus, setReservationStatus] = useState(null);
  const [isFareFetched, setIsFareFetched] = useState(false);

  const navigate = useNavigate();
  const customerId = sessionStorage.getItem("customerId");

  const fetchTotalFare = async () => {
    const reservationData = {
      trainName,
      trainId,
      transitLineName,
      originArrivalTime,
      destinationArrivalTime,
      originStationName,
      destinationStationName,
      calculatedFare,
      tripType,
      passengerType,
      reservationDate,
      customerId,
      passengerName,
    };

    try {
      const response = await axios.post(
        "http://localhost:8080/reservations/calculateTotatFare",
        reservationData
      );

      if (response.status === 200) {
        setTotalFare(response.data);
        setIsFareFetched(true);
      } else {
        alert("Failed to fetch total fare. Please try again.");
      }
    } catch (error) {
      console.error("Error fetching total fare:", error);
      alert("Error fetching total fare.");
    }
  };

  const handleReservationSubmit = async (e) => {
    e.preventDefault();

    const reservationData = {
      trainName,
      trainId,
      transitLineName,
      originArrivalTime,
      destinationArrivalTime,
      originStationName,
      destinationStationName,
      calculatedFare,
      tripType,
      passengerType,
      reservationDate,
      customerId,
      passengerName,
    };

    try {
      const response = await axios.post(
        "http://localhost:8080/reservations/makeReservation",
        reservationData
      );

      if (response.status === 201) {
        setReservationStatus("Reservation successful!");
        alert("Reservation successful!");
        navigate("/home");
      } else {
        setReservationStatus("Reservation failed. Please try again.");
        alert("Reservation failed. Please try again.");
      }
    } catch (error) {
      setReservationStatus("Error occurred while making the reservation.");
      alert("Error occurred while making the reservation.");
      console.error("Error making reservation:", error);
    }
  };

  return (
    <div className="container my-5" style={{ maxWidth: "600px" }}>
      <h1 className="text-center mb-4">Book Train Ticket</h1>
      <form
        className="border p-4 shadow rounded"
        onSubmit={handleReservationSubmit}
      >
        {state ? (
          <div>
            <div className="mb-3">
              <label className="form-label">Train Name:</label>
              <input
                className="form-control"
                type="text"
                value={trainName}
                readOnly
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Train ID:</label>
              <input
                className="form-control"
                type="text"
                value={trainId}
                readOnly
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Transit Line:</label>
              <input
                className="form-control"
                type="text"
                value={transitLineName}
                readOnly
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Origin Station:</label>
              <input
                className="form-control"
                type="text"
                value={originStationName}
                readOnly
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Destination Station:</label>
              <input
                className="form-control"
                type="text"
                value={destinationStationName}
                readOnly
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Origin Arrival Time:</label>
              <input
                className="form-control"
                type="text"
                value={originArrivalTime}
                readOnly
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Destination Arrival Time:</label>
              <input
                className="form-control"
                type="text"
                value={destinationArrivalTime}
                readOnly
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Calculated Fare:</label>
              <input
                className="form-control"
                type="text"
                value={calculatedFare}
                readOnly
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Trip Type:</label>
              <select
                className="form-select"
                value={tripType}
                onChange={(e) => setTripType(e.target.value)}
                required
              >
                <option value="">Select Trip Type</option>
                <option value="one-way">One-way</option>
                <option value="round-trip">Round-trip</option>
              </select>
            </div>
            <div className="mb-3">
              <label className="form-label">Passenger Type:</label>
              <select
                className="form-select"
                value={passengerType}
                onChange={(e) => setPassengerType(e.target.value)}
                required
              >
                <option value="">Select Passenger Type</option>
                <option value="adult">Adult</option>
                <option value="child">Child</option>
                <option value="senior">Senior</option>
                <option value="disabled">Disabled</option>
              </select>
            </div>
            <div className="mb-3">
              <label className="form-label">Reservation Date:</label>
              <input
                className="form-control"
                type="date"
                value={reservationDate}
                onChange={(e) => setReservationDate(e.target.value)}
                required
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Passenger Name:</label>
              <input
                className="form-control"
                type="text"
                value={passengerName}
                onChange={(e) => setPassengerName(e.target.value)}
                required
              />
            </div>
            <div className="mb-3">
              <label className="form-label">Total Fare:</label>
              <div className="input-group">
                <input
                  className="form-control"
                  type="text"
                  value={totalFare ? `$${totalFare}` : ""}
                  readOnly
                />
                <button
                  type="button"
                  className="btn btn-primary"
                  onClick={fetchTotalFare}
                >
                  Get Total Fare
                </button>
              </div>
            </div>
            <button
              type="submit"
              className="btn btn-success w-100"
              disabled={!isFareFetched}
            >
              Book Reservation
            </button>
          </div>
        ) : (
          <p>No train details available.</p>
        )}
      </form>
      {reservationStatus && (
        <div className="alert alert-info mt-3">{reservationStatus}</div>
      )}
    </div>
  );
}

export default TrainBooking;
