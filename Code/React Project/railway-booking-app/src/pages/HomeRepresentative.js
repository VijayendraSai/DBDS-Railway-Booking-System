import React from "react";
import { Link } from "react-router-dom"; // Import Link for navigation

function HomeRepresentative() {
  return (
    <div className="container mt-4">
      <h1 className="text-center mb-4">Welcome Railway Representative</h1>

      <div className="d-grid gap-3">
        {/* Button to navigate to ReplyService page */}
        <Link to="/reply-service">
          <button className="btn btn-primary">Reply Service</button>
        </Link>

        {/* Button to navigate to StationSchedules page */}
        <Link to="/station-schedules">
          <button className="btn btn-secondary">Station Schedules</button>
        </Link>

        {/* Button to navigate to ReservationTransitAndDate page */}
        <Link to="/customer-bookings-transit-date">
          <button className="btn btn-info">
            Customer Bookings on Transit and Date
          </button>
        </Link>
      </div>
    </div>
  );
}

export default HomeRepresentative;
