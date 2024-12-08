<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    // Get the existing session without declaring it again
    // HttpSession session = request.getSession();
    
    // Check if the user is logged in by verifying if the 'username' attribute exists in the session
    String username = (String) session.getAttribute("username");
    
    // If the username is null, redirect to the login page
    if (username == null) {
        response.sendRedirect("login.jsp");
        return; // Prevent further code execution after redirect
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Dashboard</title>
</head>
<body>
    <h2>Welcome, <%= username %>!</h2>
    <p>You are successfully logged in.</p>
    <!-- Logout Link -->
    <a href="logout.jsp">Logout</a>
</body>
</html>
