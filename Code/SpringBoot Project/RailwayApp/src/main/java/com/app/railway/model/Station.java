package com.app.railway.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "station")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT for station_id
    private int stationId;

    private String name;
    private String city;
    private String state;

    // Default constructor
    public Station() {}

    // Constructor with all fields
    public Station(String name, String city, String state) {
        this.name = name;
        this.city = city;
        this.state = state;
    }

    // Getters and Setters
    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    // Override toString(), equals(), and hashCode() if needed (optional)
}
