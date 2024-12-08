package com.servlet;

import com.dao.CustomerDAO;
import com.model.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get parameters from the registration form
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String username = request.getParameter("username");

        // Create a new Customer object and set its properties
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPassword(password);
        customer.setUsername(username);

        // Create CustomerDAO object to interact with the database
        CustomerDAO dao = new CustomerDAO();
        
        // Register customer in the database
        boolean isRegistered = dao.registerCustomer(customer);

        // Redirect to the success or failure page based on registration outcome
        if (isRegistered) {
            response.sendRedirect("registerSuccess.jsp");
        } else {
            response.sendRedirect("registerFailed.jsp");
        }
    }
}
