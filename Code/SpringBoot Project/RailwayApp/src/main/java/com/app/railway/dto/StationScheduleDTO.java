package com.app.railway.dto;

import java.time.LocalTime;

public class StationScheduleDTO {

    private String transitLineName;
    private int trainId;
    private String trainName;
    private String stationName;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    
	public StationScheduleDTO(String transitLineName, int trainId, String trainName, String stationName,
			LocalTime arrivalTime, LocalTime departureTime) {
		super();
		this.transitLineName = transitLineName;
		this.trainId = trainId;
		this.trainName = trainName;
		this.stationName = stationName;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
	}

	public String getTransitLineName() {
		return transitLineName;
	}

	public void setTransitLineName(String transitLineName) {
		this.transitLineName = transitLineName;
	}

	public int getTrainId() {
		return trainId;
	}

	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

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
	
}
