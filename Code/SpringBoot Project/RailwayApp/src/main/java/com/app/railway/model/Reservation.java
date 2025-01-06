package com.app.railway.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.time.LocalTime;
import java.time.LocalDate;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReservationId")
    private int reservationId;

    @Column(name = "ReservationDate")
    private LocalDate reservationDate;

    @ManyToOne
    @JoinColumn(name = "CustomerId", referencedColumnName = "customerId")
    private Customer customer;

    @Column(name = "TrainName")
    private String trainName;

    @ManyToOne
    @JoinColumn(name = "TrainId", referencedColumnName = "trainId")
    private Train train;

    @Column(name = "PassengerName")
    private String passengerName;

    @Column(name = "TripType")
    private String tripType;

    @Column(name = "PassengerType")
    private String passengerType;

    @ManyToOne
    @JoinColumn(name = "TransitLineName", referencedColumnName = "transitLineName")
    private TransitLine transitLine;

    @Column(name = "OriginArrivalTime")
    private LocalTime originArrivalTime;

    @Column(name = "DestinationArrivalTime")
    private LocalTime destinationArrivalTime;

    @Column(name = "OriginStationName")
    private String originStationName;

    @Column(name = "DestinationStationName")
    private String destinationStationName;

    @Column(name = "TotalFare")
    private float totalFare;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
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

    public TransitLine getTransitLine() {
        return transitLine;
    }

    public void setTransitLine(TransitLine transitLine) {
        this.transitLine = transitLine;
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
