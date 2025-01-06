import React, { useState, useEffect } from "react";
import axios from "axios";

function CustomerBookingsTransitAndDate() {
  const [transitLineName, setTransitLineName] = useState(""); // Selected transit line name
  const [transitLineNames, setTransitLineNames] = useState([]); // List of transit line names
  const [reservationDate, setReservationDate] = useState(""); // Selected reservation date
  const [customers, setCustomers] = useState([]); // List of customers with reservations
  const [errorMessage, setErrorMessage] = useState(""); // Error message

  // Fetch all transit line names when the component mounts
  useEffect(() => {
    axios
      .get("http://localhost:8080/transitLine/names")
      .then((response) => {
        setTransitLineNames(response.data);
      })
      .catch((error) => {
        console.error("Error fetching transit line names:", error);
        setErrorMessage("Failed to load transit line names.");
      });
  }, []);

  // Handle date change
  const handleReservationDateChange = (e) => {
    setReservationDate(e.target.value);
  };

  // Fetch customer reservations based on selected transit line and date
  const fetchCustomers = () => {
    if (!transitLineName.trim() || !reservationDate.trim()) {
      alert("Please select both a transit line and a reservation date.");
      return;
    }

    axios
      .get(
        `http://localhost:8080/reservations/cust-by-transit-date?transitLineName=${transitLineName}&reservationDate=${reservationDate}`
      )
      .then((response) => {
        if (response.data.length === 0) {
          setErrorMessage(
            "No customers found for the given transit line and date."
          );
        } else {
          setCustomers(response.data);
          setErrorMessage(""); // Clear the error message if customers are found
        }
      })
      .catch((error) => {
        console.error("Error fetching customer reservations:", error);
        setErrorMessage("Failed to load customer data.");
      });
  };

  return (
    <div className="container mt-4">
      <h1 className="mb-4 text-center text-primary">
        Customer Bookings for Transit Line and Date
      </h1>

      {/* Input fields */}
      <div className="row mb-4">
        {/* Transit Line Dropdown */}
        <div className="col-md-4">
          <select
            className="form-select"
            value={transitLineName}
            onChange={(e) => setTransitLineName(e.target.value)}
          >
            <option value="">Select Transit Line</option>
            {transitLineNames.map((line) => (
              <option key={line} value={line}>
                {line}
              </option>
            ))}
          </select>
        </div>
        {/* Reservation Date */}
        <div className="col-md-4">
          <input
            type="date"
            className="form-control"
            value={reservationDate}
            onChange={handleReservationDateChange}
          />
        </div>
        {/* Fetch Button */}
        <div className="col-md-4">
          <button className="btn btn-primary w-100" onClick={fetchCustomers}>
            Fetch Customers
          </button>
        </div>
      </div>

      {/* Error Message */}
      {errorMessage && (
        <div className="alert alert-danger text-center">{errorMessage}</div>
      )}

      {/* Customers Table */}
      {customers.length > 0 && (
        <div className="table-responsive">
          <table className="table table-striped table-hover">
            <thead className="table-dark">
              <tr>
                <th>Customer ID</th>
                <th>Email</th>
                <th>First Name</th>
                <th>Last Name</th>
              </tr>
            </thead>
            <tbody>
              {customers.map((customer) => (
                <tr key={customer.customerId}>
                  <td>{customer.customerId}</td>
                  <td>{customer.email}</td>
                  <td>{customer.firstName}</td>
                  <td>{customer.lastName}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}

export default CustomerBookingsTransitAndDate;
