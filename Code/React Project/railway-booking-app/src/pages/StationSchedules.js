import React, { useState, useEffect } from "react";
import axios from "axios";

function StationSchedules() {
  const [stationName, setStationName] = useState(""); // State to store the selected station name
  const [schedules, setSchedules] = useState([]); // State to store train schedules
  const [stationNames, setStationNames] = useState([]); // To store station names
  const [errorMessage, setErrorMessage] = useState(""); // State to store error messages

  // Fetch station names from the API
  useEffect(() => {
    const fetchStationNames = async () => {
      try {
        const response = await fetch("http://localhost:8080/station/names");
        if (!response.ok) {
          alert("Error fetching station names");
          return;
        }
        const data = await response.json();
        setStationNames(data);
      } catch (error) {
        console.error("Error:", error);
        alert("Error fetching station names");
      }
    };

    fetchStationNames();
  }, []);

  // Handle station name selection change
  const handleStationNameChange = (e) => {
    setStationName(e.target.value);
  };

  // Fetch schedules from the API based on the station name
  const handleSearch = async () => {
    if (!stationName.trim()) {
      alert("Please select a station name.");
      return;
    }

    axios
      .get(
        `http://localhost:8080/schedules/by-station?stationName=${stationName}`
      )
      .then((response) => {
        if (response.data.length === 0) {
          setErrorMessage("No schedules found for the given station.");
        } else {
          setSchedules(response.data);
          setErrorMessage(""); // Clear error message if schedules are found
        }
      })
      .catch((error) => {
        console.error("Error fetching train schedules:", error);
        setErrorMessage("Failed to load schedules.");
      });
  };

  return (
    <div className="container mt-4">
      <h1 className="mb-4 text-center">Train Schedules</h1>
      {errorMessage && (
        <div className="alert alert-danger mb-4">{errorMessage}</div>
      )}

      <div className="mb-3">
        <div className="row">
          <div className="col-md-4">
            <label htmlFor="stationName" className="form-label">
              <b> Station: </b>
            </label>
            <select
              id="stationName"
              className="form-select"
              value={stationName}
              onChange={handleStationNameChange}
            >
              <option value="">Select Station</option>
              {stationNames.map((station) => (
                <option key={station} value={station}>
                  {station}
                </option>
              ))}
            </select>
          </div>
          <div className="col-md-4 d-flex align-items-center">
            <button className="btn btn-primary" onClick={handleSearch}>
              Fetch Schedules
            </button>
          </div>
        </div>
      </div>

      {schedules.length > 0 && (
        <table className="table table-bordered table-striped">
          <thead className="table-dark">
            <tr>
              <th>Transit Line</th>
              <th>Train ID</th>
              <th>Train Name</th>
              <th>Station</th>
              <th>Arrival Time</th>
              <th>Departure Time</th>
            </tr>
          </thead>
          <tbody>
            {schedules.map((schedule, index) => (
              <tr key={index}>
                <td>{schedule.transitLineName}</td>
                <td>{schedule.trainId}</td>
                <td>{schedule.trainName}</td>
                <td>{schedule.stationName}</td>
                <td>{schedule.arrivalTime}</td>
                <td>{schedule.departureTime}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
}

export default StationSchedules;
