package com.app.railway.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.IdClass;

import java.time.LocalTime;

@Entity
@Table(name = "schedule")
@IdClass(ScheduleId.class) // Composite primary key for train_id and station_id
public class Schedule {

    @Id
    @Column(name = "train_id") // Ensure the correct column name
    private int trainId;

    @Id
    @Column(name = "station_id") // Ensure the correct column name
    private int stationId;

    @Column(name = "stop_order")
    private int stopOrder;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    // Many-to-one relationship with Train entity (train_id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "train_id", referencedColumnName = "trainId", foreignKey = @ForeignKey(name = "fk_train"))
    private Train train;

    // Many-to-one relationship with Station entity (station_id)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "station_id", referencedColumnName = "stationId", foreignKey = @ForeignKey(name = "fk_station"))
    private Station station;
    
    public Schedule() {}
    
    public Schedule(int trainId, int stationId, int stopOrder, LocalTime arrivalTime, LocalTime departureTime,
			Train train, Station station) {
		super();
		this.trainId = trainId;
		this.stationId = stationId;
		this.stopOrder = stopOrder;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.train = train;
		this.station = station;
	} 

	// Getters and Setters
    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getStopOrder() {
        return stopOrder;
    }

    public void setStopOrder(int stopOrder) {
        this.stopOrder = stopOrder;
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

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
