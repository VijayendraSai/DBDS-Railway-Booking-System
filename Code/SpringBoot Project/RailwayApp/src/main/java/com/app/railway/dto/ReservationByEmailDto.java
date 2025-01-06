package com.app.railway.dto;

import java.time.LocalDate;

public class ReservationByEmailDto {
    private int reservationId;
    private LocalDate reservationDate;
    private String trainName;
    private String passengerName;
    private String originStationName;
    private String destinationStationName;
    private float totalFare;

    // Constructor
    public ReservationByEmailDto(int reservationId, LocalDate reservationDate, String trainName, 
                                  String passengerName, String originStationName, 
                                  String destinationStationName, float totalFare) {
        this.reservationId = reservationId;
        this.reservationDate = reservationDate;
        this.trainName = trainName;
        this.passengerName = passengerName;
        this.originStationName = originStationName;
        this.destinationStationName = destinationStationName;
        this.totalFare = totalFare;
    }

    // Getters and Setters
    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
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

    public float getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(float totalFare) {
        this.totalFare = totalFare;
    }
}
