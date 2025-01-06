package com.app.railway.dto;

import java.time.LocalTime;

public class ScheduleUpdateDTO {
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private Integer stopOrder;  // Optional field, can be null

    // Getters and Setters
    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getStopOrder() {
        return stopOrder;
    }

    public void setStopOrder(Integer stopOrder) {
        this.stopOrder = stopOrder;
    }
}
