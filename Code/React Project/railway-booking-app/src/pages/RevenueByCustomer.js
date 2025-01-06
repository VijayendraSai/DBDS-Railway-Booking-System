import React, { useState, useEffect } from "react";
import axios from "axios";

function RevenueByCustomer() {
  const [revenueData, setRevenueData] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchRevenueData = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/admin/revenue-per-customer"
        );
        setRevenueData(response.data);
        setError("");
      } catch (err) {
        setError("Failed to fetch revenue data. Please try again later.");
      }
    };
    fetchRevenueData();
  }, []);

  return (
    <div className="container mt-5">
      <h1 className="text-center">Revenue by Customer</h1>
      {error && <div className="alert alert-danger">{error}</div>}
      {revenueData.length > 0 ? (
        <table className="table table-striped table-bordered mt-3">
          <thead className="thead-dark">
            <tr>
              <th>Customer Email</th>
              <th>Total Revenue</th>
            </tr>
          </thead>
          <tbody>
            {revenueData.map((item, index) => (
              <tr key={index}>
                <td>{item.customerEmail}</td>
                <td>${item.totalRevenue.toFixed(2)}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        !error && <p className="text-center">No revenue data available.</p>
      )}
    </div>
  );
}

export default RevenueByCustomer;
