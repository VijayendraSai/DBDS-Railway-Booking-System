// com.app.railway.dto.SalesReportDto.java
package com.app.railway.dto;

public class SalesReportDto {

    private int year;
    private int month;
    private double totalSales;
    private long totalReservations;

    public SalesReportDto(int year, int month, double totalSales, long totalReservations) {
        this.year = year;
        this.month = month;
        this.totalSales = totalSales;
        this.totalReservations = totalReservations;
    }

    // Getters and Setters
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public long getTotalReservations() {
        return totalReservations;
    }

    public void setTotalReservations(long totalReservations) {
        this.totalReservations = totalReservations;
    }
}
