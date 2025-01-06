package com.app.railway.controller;

import com.app.railway.dto.LoginRequest;
import com.app.railway.model.Employee;
import com.app.railway.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Login endpoint for admins and reps
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginEmployee(@RequestBody LoginRequest loginRequest) {
        Employee employee = employeeService.validateEmployeeLogin(loginRequest.getUsername(), loginRequest.getPassword());

        if (employee != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("empId", employee.getEmpId());  // Store the empId
            response.put("firstName", employee.getFirstName());
            response.put("lastName", employee.getLastName());
            response.put("email", employee.getEmail());  // Add email or any other employee details if needed
            response.put("role", employee.getRole().toString());  // Add role (ADMIN, REPRESENTATIVE)

            return ResponseEntity.ok(response);  // Return employee data in the response body
        } else {
            return ResponseEntity.status(401).body(Collections.singletonMap("message", "Invalid username or password."));
        }
    }
    
    @PostMapping("/logout")
    public ResponseEntity<String> logoutEmployee(HttpSession session) {
        // Invalidate the current session
        session.invalidate();

        return ResponseEntity.ok("Employee logout successful!");
    }
}
