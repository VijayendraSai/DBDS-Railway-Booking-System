package com.app.railway.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.railway.dto.CustomerReservationDTO;
import com.app.railway.dto.ReservationByEmailDto;
import com.app.railway.dto.ReservationByTransitLineDto;
import com.app.railway.dto.RevenueByCustomerDto;
import com.app.railway.dto.RevenueByTransitLineDto;
import com.app.railway.dto.SalesReportDto;
import com.app.railway.model.Reservation;

import jakarta.transaction.Transactional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	
	// Find reservations by Customer ID
    List<Reservation> findByCustomerCustomerId(int customerId);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM Reservation r WHERE r.reservationId = :reservationId AND r.customer.customerId = :customerId")
    int deleteByReservationIdAndCustomerId(@Param("reservationId") int reservationId, @Param("customerId") int customerId);
    
    @Query("SELECT r FROM Reservation r WHERE r.customer.customerId = :customerId AND r.reservationDate >= CURRENT_DATE ORDER BY r.reservationDate DESC")
    List<Reservation> findCurrentReservations(@Param("customerId") int customerId);

    @Query("SELECT r FROM Reservation r WHERE r.customer.customerId = :customerId AND r.reservationDate < CURRENT_DATE ORDER BY r.reservationDate DESC")
    List<Reservation> findPastReservations(@Param("customerId") int customerId);
    
    @Query("SELECT new com.app.railway.dto.SalesReportDto(" +
            "YEAR(r.reservationDate), MONTH(r.reservationDate), SUM(r.totalFare), COUNT(r)) " +
            "FROM Reservation r " +
            "GROUP BY YEAR(r.reservationDate), MONTH(r.reservationDate) " +
            "ORDER BY YEAR(r.reservationDate), MONTH(r.reservationDate)")
     List<SalesReportDto> getMonthlySalesReport();
    
    @Query("SELECT new com.app.railway.dto.ReservationByTransitLineDto(" +
            "r.transitLine.transitLineName, r.reservationId, r.reservationDate, r.trainName, r.passengerName, " +
            "r.originStationName, r.destinationStationName, r.totalFare) " +
            "FROM Reservation r " +
            "WHERE r.transitLine.transitLineName = :transitLineName " +
            "ORDER BY r.reservationDate")
     List<ReservationByTransitLineDto> findReservationsByTransitLineName(@Param("transitLineName") String transitLineName);
    
    @Query("SELECT new com.app.railway.dto.ReservationByEmailDto(" +
            "r.reservationId, r.reservationDate, r.trainName, r.passengerName, " +
            "r.originStationName, r.destinationStationName, r.totalFare) " +
            "FROM Reservation r " +
            "JOIN r.customer c " +
            "WHERE c.email = :email " +
            "ORDER BY r.reservationDate")
    List<ReservationByEmailDto> findReservationsByCustomerEmail(@Param("email") String email);
    
    @Query("SELECT new com.app.railway.dto.RevenueByTransitLineDto(" +
    	       "r.transitLine.transitLineName, SUM(r.totalFare)) " +
    	       "FROM Reservation r " +
    	       "GROUP BY r.transitLine.transitLineName")
    List<RevenueByTransitLineDto> findRevenueByTransitLine();
    
    @Query("SELECT new com.app.railway.dto.RevenueByCustomerDto(" +
            "c.email, SUM(r.totalFare)) " +
            "FROM Reservation r " +
            "JOIN r.customer c " +
            "GROUP BY c.email " +
            "ORDER BY SUM(r.totalFare) DESC")
     List<RevenueByCustomerDto> findRevenueByCustomerEmail();
    
    @Query(value = "SELECT t.transit_line_name AS transitLineName, COUNT(r.reservation_id) AS reservationCount " +
            "FROM reservation r " +
            "JOIN transit_line t ON r.transit_line_name = t.transit_line_name " +
            "GROUP BY t.transit_line_name " +
            "ORDER BY reservationCount DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Object[]> findTop5ActiveTransitLinesNative();
    
    @Query(value = "SELECT c.customer_id AS customerId, " +
            "c.email, " +
            "c.first_name AS firstName, " +
            "c.last_name AS lastName " +
            "FROM reservation r " +
            "JOIN customer c ON c.customer_id = r.customer_id " +
            "WHERE r.transit_line_name = :transitLineName " +
            "AND r.reservation_date = :reservationDate", 
    nativeQuery = true)
    List<Object[]> findCustomersByTransitLineAndDate(@Param("transitLineName") String transitLineName,
                                               @Param("reservationDate") String reservationDate);

}
