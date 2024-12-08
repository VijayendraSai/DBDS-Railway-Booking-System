package com.dao;

import com.model.Customer;
import com.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {

    public boolean registerCustomer(Customer customer) {
        boolean isRegistered = false;
        String sql = "INSERT INTO customer (email, first_name, last_name, password, username) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, customer.getEmail());
            ps.setString(2, customer.getFirstName());
            ps.setString(3, customer.getLastName());
            ps.setString(4, customer.getPassword());
            ps.setString(5, customer.getUsername());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                isRegistered = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isRegistered;
    }
    
    public boolean validateCustomerLogin(String username, String password) {
        boolean isValid = false;

        // SQL query to select the customer based on username and password
        String query = "SELECT * FROM customer WHERE username = ? AND password = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            // Set parameters for the prepared statement
            stmt.setString(1, username);
            stmt.setString(2, password);

            // Execute the query
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Customer found, valid credentials
                    isValid = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isValid;
    }
}
