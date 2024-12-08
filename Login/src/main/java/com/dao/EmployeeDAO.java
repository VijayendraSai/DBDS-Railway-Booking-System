package com.dao;

import com.model.Employee;
import com.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDAO {

    public boolean registerEmployee(Employee employee) {
        boolean isRegistered = false;
        
        // Get the connection to the database
        try (Connection conn = DBConnection.getConnection()) {
            // SQL query for inserting a new employee
            String query = "INSERT INTO employee (first_name, last_name, username, password, ssn, role) VALUES (?, ?, ?, ?, ?, ?)";

            // Create a prepared statement
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, employee.getFirstName());
                stmt.setString(2, employee.getLastName());
                stmt.setString(3, employee.getUsername());
                stmt.setString(4, employee.getPassword());
                stmt.setString(5, employee.getSsn());
                stmt.setString(6, employee.getRole());

                // Execute the update
                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    isRegistered = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isRegistered;
    }
}
