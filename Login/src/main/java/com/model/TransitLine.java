package com.model;

public class TransitLine {
    private String transitLineName;
    private float baseFare;
    private Station originStation;
    private Station destinationStation;
    
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

    
    
}
