package com.app.railway.dto;

import java.time.LocalTime;
import java.util.List;

public class TrainScheduleDTO {

    private String transitLineName;
    private String trainName;
    private int trainId;
    private LocalTime originArrivalTime;
    private LocalTime destinationArrivalTime;
    private double calculatedFare;
    private List<String> stops;

    // Updated constructor
    public TrainScheduleDTO(String transitLineName, String trainName, int trainId, LocalTime originArrivalTime, LocalTime destinationArrivalTime, double calculatedFare, List<String> stops) {
        this.transitLineName = transitLineName;
        this.trainName = trainName;
        this.trainId = trainId;
        this.originArrivalTime = originArrivalTime;
        this.destinationArrivalTime = destinationArrivalTime;
        this.calculatedFare = calculatedFare;
        this.stops = stops;
    }
    
    public String getTransitLineName() {
        return transitLineName;
    }

    public void setTransitLineName(String transitLineName) {
        this.transitLineName = transitLineName;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public LocalTime getOriginArrivalTime() {
        return originArrivalTime;
    }

    public void setOriginArrivalTime(LocalTime originArrivalTime) {
        this.originArrivalTime = originArrivalTime;
    }
    
    public LocalTime getDestinationArrivalTime() {
		return destinationArrivalTime;
	}

	public void setDestinationArrivalTime(LocalTime destinationArrivalTime) {
		this.destinationArrivalTime = destinationArrivalTime;
	}
    
    public double getCalculatedFare() {
        return calculatedFare;
    }

    public void setCalculatedFare(double calculatedFare) {
        this.calculatedFare = calculatedFare;
    }
    
    public List<String> getStops() {
        return stops;
    }

    public void setStops(List<String> stops) {
        this.stops = stops;
    }
    
}

