import React, { useState, useEffect } from "react";
import { Modal, Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";

function SortTrainSchedule() {
  const [origin, setOrigin] = useState("");
  const [destination, setDestination] = useState("");
  const [sortBy, setSortBy] = useState("originTime");
  const [trainSchedules, setTrainSchedules] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [selectedStops, setSelectedStops] = useState([]);
  const [stationNames, setStationNames] = useState([]); // Store station names
  const navigate = useNavigate();

  // Fetch station names on component mount
  useEffect(() => {
    const fetchStations = async () => {
      try {
        const response = await fetch("http://localhost:8080/station/names");
        const data = await response.json();
        setStationNames(data); // Store fetched station names
      } catch (error) {
        console.error("Error fetching station names:", error);
      }
    };

    fetchStations();
  }, []);

  // Handle search logic
  const handleSearch = async () => {
    if (origin === "" || destination === "") {
      alert("Please enter both origin and destination.");
      return;
    }

    let endpoint = "";

    switch (sortBy) {
      case "originTime":
        endpoint = "/origin-dest-station-sort-origin-time";
        break;
      case "destinationTime":
        endpoint = "/origin-dest-station-sort-dest-time";
        break;
      case "fare":
        endpoint = "/origin-dest-station-sort-fare";
        break;
      default:
        endpoint = "/origin-dest-station-sort-origin-time";
    }

    try {
      const response = await fetch(
        `http://localhost:8080/schedules${endpoint}?origin=${origin}&destination=${destination}`
      );

      if (!response.ok) {
        alert("Error fetching train schedules");
        return;
      }

      const data = await response.json();
      setTrainSchedules(data); // Set fetched schedules to state
    } catch (error) {
      console.error("Error:", error);
      alert("Error fetching train schedules");
    }
  };

  // Handle viewing stops modal
  const handleViewStops = (stops) => {
    setSelectedStops(stops);
    setShowModal(true);
  };

  // Handle closing modal
  const handleCloseModal = () => {
    setShowModal(false);
  };

  // Handle booking a train
  const handleBookTrain = (trainDetails) => {
    const trainWithStations = {
      ...trainDetails,
      originStationName: origin,
      destinationStationName: destination,
    };
    navigate("/train-booking", { state: trainWithStations });
  };

  return (
    <div className="d-flex justify-content-center align-items-center py-5">
      <div className="container col-md-8">
        <h1 className="text-center mb-4">Search Train Schedule with Sorting</h1>
        <form>
          <div className="row g-3">
            <div className="col-md-4">
              <label htmlFor="origin" className="form-label">
                Origin:
              </label>
              <select
                id="origin"
                className="form-select"
                value={origin}
                onChange={(e) => setOrigin(e.target.value)}
              >
                <option value="">Select Origin</option>
                {stationNames.map((station, index) => (
                  <option key={index} value={station}>
                    {station}
                  </option>
                ))}
              </select>
            </div>
            <div className="col-md-4">
              <label htmlFor="destination" className="form-label">
                Destination:
              </label>
              <select
                id="destination"
                className="form-select"
                value={destination}
                onChange={(e) => setDestination(e.target.value)}
              >
                <option value="">Select Destination</option>
                {stationNames.map((station, index) => (
                  <option key={index} value={station}>
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

          <div className="row g-3 mt-3">
            <div className="col-md-6">
              <label htmlFor="sortBy" className="form-label">
                Sort By:
              </label>
              <select
                id="sortBy"
                className="form-select"
                value={sortBy}
                onChange={(e) => setSortBy(e.target.value)}
              >
                <option value="originTime">Origin Time</option>
                <option value="destinationTime">Destination Time</option>
                <option value="fare">Fare</option>
              </select>
            </div>
            <div className="col-md-6 d-flex align-items-end">
              <button
                type="button"
                className="btn btn-primary w-100"
                onClick={handleSearch}
              >
                Search
              </button>
            </div>
          </div>
        </form>

        <div className="train-schedules mt-4">
          {trainSchedules.length > 0 ? (
            <table className="table table-striped table-hover">
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
    </div>
  );
}

export default SortTrainSchedule;
