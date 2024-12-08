package com.servlet;

import com.dao.EmployeeDAO;
import com.model.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/registerEmployee")
public class RegisterEmployeeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from the registration form
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String ssn = request.getParameter("ssn");
        String role = request.getParameter("role");

        // Create a new Employee object and set its properties
        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setSsn(ssn);
        employee.setRole(role);

        // Create EmployeeDAO object to interact with the database
        EmployeeDAO dao = new EmployeeDAO();

        // Register employee in the database
        boolean isRegistered = dao.registerEmployee(employee);

        // Redirect to the success or failure page based on registration outcome
        if (isRegistered) {
            response.sendRedirect("employeeRegisterSuccess.jsp");
        } else {
            response.sendRedirect("employeeRegisterFailed.jsp");
        }
    }
}
