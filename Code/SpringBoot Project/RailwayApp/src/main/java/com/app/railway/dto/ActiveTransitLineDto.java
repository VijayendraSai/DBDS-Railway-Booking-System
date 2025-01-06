package com.app.railway.dto;

public class ActiveTransitLineDto {
    
    private String transitLineName;
    private long reservationCount;

    public ActiveTransitLineDto(String transitLineName, long reservationCount) {
        this.transitLineName = transitLineName;
        this.reservationCount = reservationCount;
    }

    public String getTransitLineName() {
        return transitLineName;
    }

    public void setTransitLineName(String transitLineName) {
        this.transitLineName = transitLineName;
    }

    public long getReservationCount() {
        return reservationCount;
    }

    public void setReservationCount(long reservationCount) {
        this.reservationCount = reservationCount;
    }
}
