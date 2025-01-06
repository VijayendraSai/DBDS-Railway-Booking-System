package com.app.railway.controller;

import com.app.railway.dto.LoginRequest;
import com.app.railway.model.Customer;
import com.app.railway.service.CustomerService;

import jakarta.servlet.http.HttpSession;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Register a new customer
    @PostMapping("/register")
    public ResponseEntity<String> registerCustomer(@RequestBody Customer customer) {
        // Check if email or username is already taken
        if (customerService.isEmailOrUsernameTaken(customer.getEmail(), customer.getUsername())) {
            return ResponseEntity.badRequest().body("Email or Username is already taken");
        }

        // Register the customer
        customerService.registerCustomer(customer);

        return ResponseEntity.ok("Customer registered successfully!");
    }
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginCustomer(@RequestBody LoginRequest loginRequest) {
        Customer customer = customerService.validateCustomerLogin(loginRequest.getUsername(), loginRequest.getPassword());
        
        if (customer != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("customerId", customer.getCustomerId());  // Store the customerId
            response.put("firstName", customer.getFirstName());
            response.put("lastName", customer.getLastName());
            response.put("email", customer.getEmail());  // Add any other customer details if needed
            
            return ResponseEntity.ok(response);  // Return customer data in the response body
        } else {
            return ResponseEntity.status(401).body(Collections.singletonMap("message", "Invalid username or password."));
        }
    }

    
    @PostMapping("/logout")
    public ResponseEntity<String> logoutCustomer(HttpSession session) {
        // Invalidate the current session
        session.invalidate();
        return ResponseEntity.ok("Customer logout successful!");
    }
}
