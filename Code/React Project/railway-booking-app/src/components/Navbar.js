import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

function Navbar() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userRole, setUserRole] = useState(null); // To store the user role (EMPLOYEE, CUSTOMER)
  const navigate = useNavigate();

  useEffect(() => {
    // Function to check login status and user role
    const checkLoginStatus = () => {
      const customerId = sessionStorage.getItem("customerId");
      const empId = sessionStorage.getItem("empId");

      if (customerId || empId) {
        setIsLoggedIn(true);
        setUserRole(empId ? sessionStorage.getItem("role") : "CUSTOMER");
      } else {
        setIsLoggedIn(false);
        setUserRole(null);
      }
    };

    // Check login status when the component mounts
    checkLoginStatus();

    // Optionally, use an interval to recheck sessionStorage if necessary
    const interval = setInterval(checkLoginStatus, 1000); // Recheck every second
    return () => clearInterval(interval); // Clear the interval when the component unmounts
  }, []); // Run only once after initial render

  const handleLogout = () => {
    // Clear sessionStorage on logout
    sessionStorage.clear();
    setIsLoggedIn(false); // Update the state to reflect logout
    setUserRole(null); // Clear user role
    navigate("/login"); // Redirect to login page
  };

  const goToHomeCustomer = () => navigate("/home"); // Redirect to home page for customer
  const goToHomeRepresentative = () => navigate("/home-representative"); // Redirect to home page for representative
  const goToHomeAdmin = () => navigate("/home-admin"); // Redirect to home page for admin

  return (
    <nav className="navbar navbar-expand-lg">
      <a className="navbar-brand" href="/">
        Railway
      </a>
      <button
        className="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarNav"
        aria-controls="navbarNav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span className="navbar-toggler-icon"></span>
      </button>
      <div className="collapse navbar-collapse" id="navbarNav">
        <ul className="navbar-nav ml-auto">
          {isLoggedIn ? (
            <>
              {userRole === "REPRESENTATIVE" && (
                <>
                  <li className="nav-item">
                    <button
                      className="btn btn-primary"
                      onClick={goToHomeRepresentative}
                    >
                      Home (Representative)
                    </button>
                  </li>
                  <li className="nav-item">
                    <button className="btn btn-danger" onClick={handleLogout}>
                      Logout
                    </button>
                  </li>
                </>
              )}
              {userRole === "ADMIN" && (
                <>
                  <li className="nav-item">
                    <button className="btn btn-primary" onClick={goToHomeAdmin}>
                      Home (Admin)
                    </button>
                  </li>
                  <li className="nav-item">
                    <button className="btn btn-danger" onClick={handleLogout}>
                      Logout
                    </button>
                  </li>
                </>
              )}
              {userRole === "CUSTOMER" && (
                <>
                  <li className="nav-item">
                    <button
                      className="btn btn-primary"
                      onClick={goToHomeCustomer}
                    >
                      Home (Customer)
                    </button>
                  </li>
                  <li className="nav-item">
                    <button className="btn btn-danger" onClick={handleLogout}>
                      Logout
                    </button>
                  </li>
                </>
              )}
            </>
          ) : (
            <>
              <li className="nav-item">
                <a className="nav-link" href="/register">
                  Register
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="/login">
                  Login
                </a>
              </li>
              <li className="nav-item">
                <a className="nav-link" href="/login-employee">
                  Login Employee
                </a>
              </li>
            </>
          )}
        </ul>
      </div>
    </nav>
  );
}

export default Navbar;
