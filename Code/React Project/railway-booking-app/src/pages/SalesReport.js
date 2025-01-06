import React, { useEffect, useState } from "react";
import axios from "axios";

function SalesReport() {
  const [salesReports, setSalesReports] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchSalesReports = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/admin/sales-report"
        );
        setSalesReports(response.data);
      } catch (err) {
        setError("Failed to fetch sales reports. Please try again.");
      }
    };

    fetchSalesReports();
  }, []);

  return (
    <div className="container mt-5">
      <h1 className="text-center">Monthly Sales Report</h1>
      {error && <div className="alert alert-danger">{error}</div>}
      {salesReports.length > 0 ? (
        <table className="table table-striped table-bordered mt-3">
          <thead className="thead-dark">
            <tr>
              <th>Year</th>
              <th>Month</th>
              <th>Total Sales</th>
              <th>Total Reservations</th>
            </tr>
          </thead>
          <tbody>
            {salesReports.map((report, index) => (
              <tr key={index}>
                <td>{report.year}</td>
                <td>{report.month}</td>
                <td>${report.totalSales.toFixed(2)}</td>
                <td>{report.totalReservations}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        !error && <p className="text-center">Loading sales reports...</p>
      )}
    </div>
  );
}

export default SalesReport;
