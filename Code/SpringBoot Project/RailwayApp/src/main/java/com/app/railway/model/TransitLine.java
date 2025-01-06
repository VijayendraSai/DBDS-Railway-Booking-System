package com.app.railway.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transit_line")
public class TransitLine {

    @Id
    private String transitLineName; // Primary key

    private float baseFare;
    private int totalStops;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_station_id", referencedColumnName = "stationId", foreignKey = @ForeignKey(name = "fk_origin_station"))
    private Station originStation; // Foreign key to Station

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_station_id", referencedColumnName = "stationId", foreignKey = @ForeignKey(name = "fk_destination_station"))
    private Station destinationStation; // Foreign key to Station

    // Default constructor
    public TransitLine() {}

    // Constructor with all fields
    public TransitLine(String transitLineName, float baseFare, int totalStops, Station originStation, Station destinationStation) {
        this.transitLineName = transitLineName;
        this.baseFare = baseFare;
        this.totalStops = totalStops;
        this.originStation = originStation;
        this.destinationStation = destinationStation;
    }

    public TransitLine(String transitLineName) {
		super();
		this.transitLineName = transitLineName;
	}

	// Getters and Setters
    public String getTransitLineName() {
        return transitLineName;
    }

    public void setTransitLineName(String transitLineName) {
        this.transitLineName = transitLineName;
    }

    public float getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(float baseFare) {
        this.baseFare = baseFare;
    }

    public int getTotalStops() {
        return totalStops;
    }

    public void setTotalStops(int totalStops) {
        this.totalStops = totalStops;
    }

    public Station getOriginStation() {
        return originStation;
    }

    public void setOriginStation(Station originStation) {
        this.originStation = originStation;
    }

    public Station getDestinationStation() {
        return destinationStation;
    }

    public void setDestinationStation(Station destinationStation) {
        this.destinationStation = destinationStation;
    }

    // Override toString(), equals(), and hashCode() if needed (optional)
}
