<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    //HttpSession session = request.getSession();
    session.invalidate();  // Invalidates the session
    response.sendRedirect("login.jsp");  // Redirect to login page after logout
%>
