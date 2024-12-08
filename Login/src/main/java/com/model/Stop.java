package com.model;

public class Stop {
    private int stopId;
    private TrainSchedule schedule;
    private Station station;
    private int stopOrder;
    private String arrivalTime;
    private String departureTime;
    
    // Getters and Setters
	public int getStopId() {
		return stopId;
	}
	public void setStopId(int stopId) {
		this.stopId = stopId;
	}
	public TrainSchedule getSchedule() {
		return schedule;
	}
	public void setSchedule(TrainSchedule schedule) {
		this.schedule = schedule;
	}
	public Station getStation() {
		return station;
	}
	public void setStation(Station station) {
		this.station = station;
	}
	public int getStopOrder() {
		return stopOrder;
	}
	public void setStopOrder(int stopOrder) {
		this.stopOrder = stopOrder;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

    
}
