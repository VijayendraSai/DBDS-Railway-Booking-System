import React from "react";
import { Link } from "react-router-dom"; // Import Link for navigation

function Home() {
  return (
    <div className="container text-center py-5">
      <h1 className="mb-4">Welcome to the Railway System</h1>

      {/* Button or Link to navigate to SearchTrainSchedule page */}
      <div className="mb-3">
        <Link to="/search-train-schedule">
          <button className="btn btn-primary btn-lg">
            Search Train Schedules
          </button>
        </Link>
      </div>

      {/* Button or Link to navigate to SortTrainSchedule page */}
      <div className="mb-3">
        <Link to="/sort-train-schedule">
          <button className="btn btn-warning btn-lg">
            Sort Train Schedules
          </button>
        </Link>
      </div>

      {/* Button to navigate to MyReservations page */}
      <div className="mb-3">
        <Link to="/my-reservations">
          <button className="btn btn-success btn-lg">
            View My Reservations
          </button>
        </Link>
      </div>

      {/* Button to navigate to CancelReservation page */}
      <div className="mb-3">
        <Link to="/cancel-reservation">
          <button className="btn btn-danger btn-lg">Cancel Reservation</button>
        </Link>
      </div>

      {/* Button to navigate to CustomerService page */}
      <div className="mb-3">
        <Link to="/customer-service">
          <button className="btn btn-info btn-lg">Customer Service</button>
        </Link>
      </div>
    </div>
  );
}

export default Home;
