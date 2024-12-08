package com.dao;

import com.model.TrainSchedule;
import com.model.Station;
import com.model.Stop;
import com.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainScheduleDAO {

    public List<TrainSchedule> searchSchedules(String origin, String destination, String date) {
        List<TrainSchedule> schedules = new ArrayList<>();
        String query = "SELECT * FROM TrainSchedules ts " +
                       "JOIN TransitLine tl ON ts.transit_line_name = tl.transit_line_name " +
                       "WHERE tl.origin_station = ? AND tl.destination_station = ? AND ts.origin_departure LIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, origin);
            stmt.setString(2, destination);
            stmt.setString(3, date + "%"); // Match date and time

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TrainSchedule schedule = new TrainSchedule();
                schedule.setScheduleId(rs.getInt("schedule_id"));
                // Fill the TrainSchedule object with data from the result set
                // Add stops
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedules;
    }
    
    public TrainSchedule getScheduleById(int scheduleId) {
        TrainSchedule schedule = null;
        String query = "SELECT * FROM TrainSchedules ts " +
                       "JOIN TransitLine tl ON ts.transit_line_name = tl.transit_line_name " +
                       "WHERE ts.schedule_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, scheduleId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                schedule = new TrainSchedule();
                schedule.setScheduleId(rs.getInt("schedule_id"));

                // Fill in the other attributes for the TrainSchedule
                schedule.setOriginDeparture(rs.getString("origin_departure"));
                schedule.setDestinationArrival(rs.getString("destination_arrival"));
                // You can also set TransitLine and Train objects if needed
                // Example:
                // schedule.setTransitLine(new TransitLine(rs.getString("transit_line_name")));
                // schedule.setTrain(new Train(rs.getInt("train_id"), rs.getString("train_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return schedule;
    }

    public List<Stop> getStopsForSchedule(int scheduleId) {
        List<Stop> stops = new ArrayList<>();
        String query = "SELECT * FROM Stops WHERE schedule_id = ? ORDER BY stop_order";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, scheduleId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Stop stop = new Stop();
                // Fill stop object
                stops.add(stop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stops;
    }
}
