package com.servlet;


import com.model.TrainSchedule;
import com.dao.TrainScheduleDAO;
import com.model.Stop;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.List;

public class TrainScheduleServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));

        TrainScheduleDAO dao = new TrainScheduleDAO();
        TrainSchedule schedule = dao.getScheduleById(scheduleId);
        List<Stop> stops = dao.getStopsForSchedule(scheduleId);

        request.setAttribute("schedule", schedule);
        request.setAttribute("stops", stops);
        RequestDispatcher dispatcher = request.getRequestDispatcher("schedule.jsp");
        dispatcher.forward(request, response);
    }
}
