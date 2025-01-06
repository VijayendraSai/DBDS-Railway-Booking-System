import React, { useEffect, useState } from "react";
import { Table, Spinner } from "react-bootstrap";

function AllRepresentative() {
  const [representatives, setRepresentatives] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Fetch data from the API
    const fetchRepresentatives = async () => {
      try {
        const response = await fetch(
          "http://localhost:8080/admin/representatives"
        );
        if (!response.ok) {
          throw new Error("Failed to fetch representatives");
        }
        const data = await response.json();
        setRepresentatives(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchRepresentatives();
  }, []);

  return (
    <div className="container mt-5">
      <h2 className="mb-4">All Representatives</h2>
      {loading ? (
        <div className="text-center">
          <Spinner animation="border" role="status">
            <span className="visually-hidden">Loading...</span>
          </Spinner>
        </div>
      ) : error ? (
        <div className="alert alert-danger" role="alert">
          {error}
        </div>
      ) : (
        <Table striped bordered hover>
          <thead>
            <tr>
              <th>Emp Id</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Email</th>
              <th>Username</th>
              <th>SSN</th>
              <th>Role</th>
            </tr>
          </thead>
          <tbody>
            {representatives.map((rep, index) => (
              <tr>
                <td>{rep.empId}</td>
                <td>{rep.firstName}</td>
                <td>{rep.lastName}</td>
                <td>{rep.email}</td>
                <td>{rep.username}</td>
                <td>{rep.ssn}</td>
                <td>{rep.role}</td>
              </tr>
            ))}
          </tbody>
        </Table>
      )}
    </div>
  );
}

export default AllRepresentative;
