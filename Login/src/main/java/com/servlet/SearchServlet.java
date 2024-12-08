package com.servlet;

import com.dao.TrainScheduleDAO;
import com.model.TrainSchedule;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;

public class SearchServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String origin = request.getParameter("origin");
        String destination = request.getParameter("destination");
        String date = request.getParameter("date");

        TrainScheduleDAO dao = new TrainScheduleDAO();
        List<TrainSchedule> schedules = dao.searchSchedules(origin, destination, date);

        request.setAttribute("schedules", schedules);
        RequestDispatcher dispatcher = request.getRequestDispatcher("results.jsp");
        dispatcher.forward(request, response);
    }
}
