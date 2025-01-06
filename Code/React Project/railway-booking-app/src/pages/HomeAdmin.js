import React from "react";
import { Link } from "react-router-dom";

function HomeAdmin() {
  return (
    <div className="container mt-4">
      <h1 className="text-center mb-3">Welcome Railway Admin</h1>

      <div className="d-grid gap-3">
        {/* Button to navigate to All Representative page */}
        <Link to="/all-representative">
          <button className="btn btn-primary">{"All Representative"}</button>
        </Link>

        {/* Button to navigate to Add Representative page */}
        <Link to="/add-representative">
          <button className="btn btn-success">{"Add Representative"}</button>
        </Link>

        {/* Button to navigate to Edit Representative page */}
        <Link to="/edit-representative">
          <button className="btn btn-warning">{"Edit Representative"}</button>
        </Link>

        {/* Button to navigate to Delete Representative page */}
        <Link to="/delete-representative">
          <button className="btn btn-danger">{"Delete Representative"}</button>
        </Link>

        {/* Button to navigate to Sales Report page */}
        <Link to="/sales-report">
          <button className="btn btn-info">{"Sales Report"}</button>
        </Link>

        {/* Button to navigate to Reservations by Transit Line page */}
        <Link to="/reservations-transit-line">
          <button className="btn btn-secondary">
            {"Reservation By Transit Line"}
          </button>
        </Link>

        {/* Button to navigate to Reservations by Customer page */}
        <Link to="/reservations-customer">
          <button className="btn btn-dark">{"Reservation By Customer"}</button>
        </Link>

        {/* Button to navigate to Revenue by Transit Line page */}
        <Link to="/revenue-transit-line">
          <button className="btn btn-primary">
            {"Revenue By Transit Line"}
          </button>
        </Link>

        {/* Button to navigate to Revenue by Customer page */}
        <Link to="/revenue-customer">
          <button className="btn btn-info">{"Revenue By Customer"}</button>
        </Link>

        {/* Button to navigate to Best Customer page */}
        <Link to="/get-best-customer">
          <button className="btn btn-success">{"Best Customer"}</button>
        </Link>

        {/* Button to navigate to Top 5 Transit Lines page */}
        <Link to="/top-trainsit-lines">
          <button className="btn btn-warning">{"Top Transit Lines"}</button>
        </Link>
      </div>
    </div>
  );
}

export default HomeAdmin;
