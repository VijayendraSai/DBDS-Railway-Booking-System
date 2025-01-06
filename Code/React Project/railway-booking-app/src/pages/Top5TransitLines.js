import React, { useState, useEffect } from "react";
import axios from "axios";

function Top5TransitLines() {
  const [topTransitLines, setTopTransitLines] = useState([]);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchTop5TransitLines = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/admin/top-5-active-transit-lines"
        );
        setTopTransitLines(response.data);
        setError("");
      } catch (err) {
        setError(
          "Failed to fetch the top 5 active transit lines. Please try again later."
        );
      }
    };

    fetchTop5TransitLines();
  }, []);

  return (
    <div className="container mt-5">
      <h1 className="text-center">Top 5 Most Active Transit Lines</h1>
      {error && <div className="alert alert-danger">{error}</div>}
      {topTransitLines.length > 0 ? (
        <table className="table table-striped table-bordered mt-3">
          <thead className="thead-dark">
            <tr>
              <th>Transit Line Name</th>
              <th>Reservation Count</th>
            </tr>
          </thead>
          <tbody>
            {topTransitLines.map((line, index) => (
              <tr key={index}>
                <td>{line.transitLineName}</td>
                <td>{line.reservationCount}</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        !error && (
          <p className="text-center">
            No data available for active transit lines.
          </p>
        )
      )}
    </div>
  );
}

export default Top5TransitLines;
