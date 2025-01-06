package com.app.railway.dto;

public class RevenueByCustomerDto {
    private String customerEmail;
    private Double totalRevenue;

    public RevenueByCustomerDto(String customerEmail, Double totalRevenue) {
        this.customerEmail = customerEmail;
        this.totalRevenue = totalRevenue;
    }

    // Getters and Setters
    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
