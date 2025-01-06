import React, { useState, useEffect } from "react";
import axios from "axios";

function BestCustomer() {
  const [bestCustomer, setBestCustomer] = useState(null);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchBestCustomer = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/admin/best-customer"
        );
        setBestCustomer(response.data);
        setError("");
      } catch (err) {
        setError(
          "Failed to fetch the best customer data. Please try again later."
        );
      }
    };

    fetchBestCustomer();
  }, []);

  return (
    <div className="container mt-5">
      <h1 className="text-center">Best Customer</h1>
      {error && <div className="alert alert-danger">{error}</div>}
      {bestCustomer ? (
        <table className="table table-striped table-bordered mt-3">
          <thead className="thead-dark">
            <tr>
              <th>Customer Email</th>
              <th>Total Revenue</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>{bestCustomer.customerEmail}</td>
              <td>${bestCustomer.totalRevenue.toFixed(2)}</td>
            </tr>
          </tbody>
        </table>
      ) : (
        !error && (
          <p className="text-center">No best customer data available.</p>
        )
      )}
    </div>
  );
}

export default BestCustomer;
