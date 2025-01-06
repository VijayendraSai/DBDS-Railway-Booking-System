package com.app.railway.controller;

import com.app.railway.dto.CustomerReservationDTO;
import com.app.railway.dto.ReservationDTO;
import com.app.railway.model.Reservation;
import com.app.railway.service.ReservationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/makeReservation")
    public ResponseEntity<Reservation> makeReservation(@RequestBody ReservationDTO reservationDTO) {
        try {
            // Call service method to create the reservation
            Reservation reservation = reservationService.makeReservation(reservationDTO);
            
            // Return the created reservation with HTTP 201 status
            return new ResponseEntity<>(reservation, HttpStatus.CREATED);
        } catch (Exception e) {
            // Handle exceptions (e.g., invalid input, service issues)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/calculateTotatFare")
    public Float calculateTotalFareReservation(@RequestBody ReservationDTO reservationDTO) {
        try {
            // Call service method to calculate the fare
            float total_fare = reservationService.calculateFare(reservationDTO);
            
            // Return the total fare with HTTP 201 status
            //return new ResponseEntity<>(total_fare, HttpStatus.CREATED);
            return total_fare;
        } catch (Exception e) {
            // Handle exceptions (e.g., invalid input, service issues)
            //return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        	return null;
        }
    }
    
    // Endpoint to get all reservations for a customer by customerId
    @GetMapping("/getReservations/{customerId}")
    public List<Reservation> getReservations(@PathVariable int customerId) {
        return reservationService.getReservationsByCustomerId(customerId);
    }
    
    @DeleteMapping("/cancel/{reservationId}")
    public String cancelReservation(
            @PathVariable int reservationId,
            @RequestParam int customerId) {
        
        return reservationService.cancelReservation(reservationId, customerId);
    }
    
    @GetMapping("/current")
    public ResponseEntity<List<Reservation>> getCurrentReservations(@RequestParam int customerId) {
        try {
            List<Reservation> currentReservations = reservationService.getCurrentReservations(customerId);
            return new ResponseEntity<>(currentReservations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/past")
    public ResponseEntity<List<Reservation>> getPastReservations(@RequestParam int customerId) {
        try {
            List<Reservation> pastReservations = reservationService.getPastReservations(customerId);
            return new ResponseEntity<>(pastReservations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/cust-by-transit-date")
    public List<CustomerReservationDTO> getCustomersByTransitLineAndDate(
            @RequestParam("transitLineName") String transitLineName,
            @RequestParam("reservationDate") String reservationDate) {
        return reservationService.getCustomersByTransitLineAndDate(transitLineName, reservationDate);
    }
}
