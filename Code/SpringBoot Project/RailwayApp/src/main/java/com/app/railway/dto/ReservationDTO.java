package com.app.railway.dto;

import java.time.LocalDate;

public class ReservationDTO {

	private LocalDate reservationDate;
    private int customerId;
    private String passengerName;
    private String trainName;
    private String trainId;
    private String transitLineName;
    private String originArrivalTime;
    private String destinationArrivalTime;
    private String originStationName;
    private String destinationStationName;
    private float calculatedFare;
    private String tripType;
    private String passengerType;
    
	public ReservationDTO(LocalDate reservationDate, int customerId, String passengerName, String trainName, String trainId, String transitLineName, String originArrivalTime,
			String destinationArrivalTime, String originStationName, String destinationStationName,
			float calculatedFare, String tripType, String passengerType) {
		super();
		this.reservationDate = reservationDate;
		this.customerId = customerId;
		this.passengerName = passengerName;
		this.trainName = trainName;
		this.trainId = trainId;
		this.transitLineName = transitLineName;
		this.originArrivalTime = originArrivalTime;
		this.destinationArrivalTime = destinationArrivalTime;
		this.originStationName = originStationName;
		this.destinationStationName = destinationStationName;
		this.calculatedFare = calculatedFare;
		this.tripType = tripType;
		this.passengerType = passengerType;
	}

	// Getters and setters
	public LocalDate getReservationDate() {
		return reservationDate;
	}

	public void setReservationDate(LocalDate reservationDate) {
		this.reservationDate = reservationDate;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	
	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getTrainId() {
		return trainId;
	}

	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}

	public String getTransitLineName() {
		return transitLineName;
	}

	public void setTransitLineName(String transitLineName) {
		this.transitLineName = transitLineName;
	}

	public String getOriginArrivalTime() {
		return originArrivalTime;
	}

	public void setOriginArrivalTime(String originArrivalTime) {
		this.originArrivalTime = originArrivalTime;
	}

	public String getDestinationArrivalTime() {
		return destinationArrivalTime;
	}

	public void setDestinationArrivalTime(String destinationArrivalTime) {
		this.destinationArrivalTime = destinationArrivalTime;
	}

	public String getOriginStationName() {
		return originStationName;
	}

	public void setOriginStationName(String originStationName) {
		this.originStationName = originStationName;
	}

	public String getDestinationStationName() {
		return destinationStationName;
	}

	public void setDestinationStationName(String destinationStationName) {
		this.destinationStationName = destinationStationName;
	}

	public float getCalculatedFare() {
		return calculatedFare;
	}

	public void setCalculatedFare(float calculatedFare) {
		this.calculatedFare = calculatedFare;
	}

	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}

	public String getPassengerType() {
		return passengerType;
	}

	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}
    
}
