package com.model;

import java.util.List;

public class TrainSchedule {
    private int scheduleId;
    private TransitLine transitLine;
    private Train train;
    private String originDeparture;
    private String destinationArrival;
    private List<Stop> stops;
    
    // Getters and Setters
	public int getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}
	public TransitLine getTransitLine() {
		return transitLine;
	}
	public void setTransitLine(TransitLine transitLine) {
		this.transitLine = transitLine;
	}
	public Train getTrain() {
		return train;
	}
	public void setTrain(Train train) {
		this.train = train;
	}
	public String getOriginDeparture() {
		return originDeparture;
	}
	public void setOriginDeparture(String originDeparture) {
		this.originDeparture = originDeparture;
	}
	public String getDestinationArrival() {
		return destinationArrival;
	}
	public void setDestinationArrival(String destinationArrival) {
		this.destinationArrival = destinationArrival;
	}
	public List<Stop> getStops() {
		return stops;
	}
	public void setStops(List<Stop> stops) {
		this.stops = stops;
	}
    
}
