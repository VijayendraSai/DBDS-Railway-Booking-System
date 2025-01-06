package com.app.railway.dto;

public class RevenueByTransitLineDto {
    private String transitLineName;
    private Double totalRevenue;

    // Constructor
    public RevenueByTransitLineDto(String transitLineName, Double totalRevenue) {
        this.transitLineName = transitLineName;
        this.totalRevenue = totalRevenue;
    }

    // Getters and setters
    public String getTransitLineName() {
        return transitLineName;
    }

    public void setTransitLineName(String transitLineName) {
        this.transitLineName = transitLineName;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}

