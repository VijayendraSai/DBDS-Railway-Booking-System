import React, { useState, useEffect } from "react";
import { Modal, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

function SearchTrainSchedule() {
  const [origin, setOrigin] = useState("");
  const [destination, setDestination] = useState("");
  const [stationNames, setStationNames] = useState([]); // To store station names
  const [trainSchedules, setTrainSchedules] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [selectedStops, setSelectedStops] = useState([]);
  const navigate = useNavigate();

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

  const handleSearch = async () => {
    if (origin === "" || destination === "") {
      alert("Please select both origin and destination.");
      return;
    }

    try {
      const response = await fetch(
        `http://localhost:8080/schedules/origin-dest-station?origin=${origin}&destination=${destination}`
      );

      if (!response.ok) {
        alert("Error fetching train schedules");
        return;
      }

      const data = await response.json();
      setTrainSchedules(data);
    } catch (error) {
      console.error("Error:", error);
      alert("Error fetching train schedules");
    }
  };

  const handleViewStops = (stops) => {
    setSelectedStops(stops);
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  const handleBookTrain = (trainDetails) => {
    const trainWithStations = {
      ...trainDetails,
      originStationName: origin,
      destinationStationName: destination,
    };
    navigate("/train-booking", { state: trainWithStations });
  };

  return (
    <div className="container mt-5">
      <h1 className="text-center mb-4">Search Train Schedule</h1>
      <form className="mb-4">
        <div className="row mb-3">
          <div className="col-md-4">
            <label htmlFor="origin" className="form-label">
              Origin Station:
            </label>
            <select
              id="origin"
              className="form-select"
              value={origin}
              onChange={(e) => setOrigin(e.target.value)}
            >
              <option value="">Select Origin</option>
              {stationNames.map((station) => (
                <option key={station} value={station}>
                  {station}
                </option>
              ))}
            </select>
          </div>
          <div className="col-md-4">
            <label htmlFor="destination" className="form-label">
              Destination Station:
            </label>
            <select
              id="destination"
              className="form-select"
              value={destination}
              onChange={(e) => setDestination(e.target.value)}
            >
              <option value="">Select Destination</option>
              {stationNames.map((station) => (
                <option key={station} value={station}>
                  {station}
                </option>
              ))}
            </select>
          </div>
          <div className="col-md-4">
            <label htmlFor="date" className="form-label">
              Date:
            </label>
            <input type="date" id="date" className="form-control" />
          </div>
        </div>
        <div className="text-center">
          <button
            type="button"
            className="btn btn-primary"
            onClick={handleSearch}
          >
            Search
          </button>
        </div>
      </form>

      <div className="train-schedules">
        {trainSchedules.length > 0 ? (
          <table className="table table-bordered table-striped mt-4">
            <thead className="table-dark">
              <tr>
                <th>Train Name</th>
                <th>Transit Line</th>
                <th>Fare</th>
                <th>Origin Time</th>
                <th>Destination Time</th>
                <th>Stops</th>
                <th>Book</th>
              </tr>
            </thead>
            <tbody>
              {trainSchedules.map((schedule) => (
                <tr key={schedule.trainId}>
                  <td>{schedule.trainName}</td>
                  <td>{schedule.transitLineName}</td>
                  <td>{schedule.calculatedFare}</td>
                  <td>{schedule.originArrivalTime}</td>
                  <td>{schedule.destinationArrivalTime}</td>
                  <td>
                    <button
                      className="btn btn-link"
                      onClick={() => handleViewStops(schedule.stops)}
                    >
                      View Stops
                    </button>
                  </td>
                  <td>
                    <button
                      className="btn btn-success"
                      onClick={() => handleBookTrain(schedule)}
                    >
                      Book
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        ) : (
          <p className="text-center">No schedules found</p>
        )}
      </div>

      <Modal show={showModal} onHide={handleCloseModal}>
        <Modal.Header closeButton>
          <Modal.Title>Train Stops</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <ul>
            {selectedStops.map((stop, index) => (
              <li key={index}>{stop}</li>
            ))}
          </ul>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleCloseModal}>
            Close
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
}

export default SearchTrainSchedule;
