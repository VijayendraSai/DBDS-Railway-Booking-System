package com.servlet;

import com.dao.CustomerDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Instantiate CustomerDAO to validate the login
        CustomerDAO customerDAO = new CustomerDAO();
        
        boolean isValid = customerDAO.validateCustomerLogin(username, password);
        
        if (isValid) {
            // Create a session and store the customer details in the session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            response.sendRedirect("dashboard.jsp");  // Redirect to a dashboard or home page
        } else {
            // If login is invalid, send a message to the user
            request.setAttribute("errorMessage", "Invalid username or password.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);  // Forward back to the login page
        }
    }
}
