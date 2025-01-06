package com.app.railway.service;

import com.app.railway.model.Customer;
import com.app.railway.model.Reservation;
import com.app.railway.model.Train;
import com.app.railway.model.TransitLine;
import com.app.railway.dao.ReservationRepository;
import com.app.railway.dto.CustomerReservationDTO;
import com.app.railway.dto.ReservationDTO;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public float calculateFare(ReservationDTO reservationDTO) {
        // Basic fare calculation: 1-way or round-trip
        float baseFare = reservationDTO.getCalculatedFare();
        if (reservationDTO.getTripType().equalsIgnoreCase("round-trip")) {
            baseFare *= 2; // Double the fare for round-trip
        }

        // Apply discount based on passenger type
        switch (reservationDTO.getPassengerType().toLowerCase()) {
            case "child":
                return baseFare * 0.75f; // 25% discount
            case "senior":
                return baseFare * 0.65f; // 35% discount
            case "disabled":
                return baseFare * 0.50f; // 50% discount
            case "adult":
            	return baseFare;
            default:
                return baseFare; // No discount
        }
    }

    public Reservation makeReservation(ReservationDTO reservationDTO) {
        // Calculate the total fare based on the input
        float totalFare = calculateFare(reservationDTO);

        // Create the Reservation object
        Reservation reservation = new Reservation();
        reservation.setReservationDate(reservationDTO.getReservationDate() != null ? reservationDTO.getReservationDate() : LocalDate.now());
        reservation.setCustomer(new Customer(reservationDTO.getCustomerId()));
        reservation.setPassengerName(reservationDTO.getPassengerName());
        reservation.setTrainName(reservationDTO.getTrainName());
        reservation.setTrain(new Train(Integer.parseInt(reservationDTO.getTrainId()))); // assuming Train is available
        reservation.setTransitLine(new TransitLine(reservationDTO.getTransitLineName())); // assuming TransitLine is available
        reservation.setOriginArrivalTime(LocalTime.parse(reservationDTO.getOriginArrivalTime()));
        reservation.setDestinationArrivalTime(LocalTime.parse(reservationDTO.getDestinationArrivalTime()));
        reservation.setOriginStationName(reservationDTO.getOriginStationName());
        reservation.setDestinationStationName(reservationDTO.getDestinationStationName());
        reservation.setTotalFare(totalFare);
        reservation.setTripType(reservationDTO.getTripType());
        reservation.setPassengerType(reservationDTO.getPassengerType());

        // Save the reservation
        return reservationRepository.save(reservation);
    }
    
    // Get reservations by customerId
    public List<Reservation> getReservationsByCustomerId(int customerId) {
        return reservationRepository.findByCustomerCustomerId(customerId);
    }

    public String cancelReservation(int reservationId, int customerId) {
        try {
            reservationRepository.deleteByReservationIdAndCustomerId(reservationId, customerId);
            return "Reservation canceled successfully.";
        } catch (Exception e) {
            return "Reservation cancellation failed: " + e.getMessage();
        }
    }
    
    public List<Reservation> getCurrentReservations(int customerId) {
        return reservationRepository.findCurrentReservations(customerId);
    }

    public List<Reservation> getPastReservations(int customerId) {
        return reservationRepository.findPastReservations(customerId);
    }
    
    public List<CustomerReservationDTO> getCustomersByTransitLineAndDate(String transitLineName, String reservationDate) {
        // Fetch the raw data as Object[]
        List<Object[]> results = reservationRepository.findCustomersByTransitLineAndDate(transitLineName, reservationDate);

        // Convert Object[] to CustomerReservationDTO
        return results.stream().map(result -> new CustomerReservationDTO(
                (Integer) result[0],  // customerId
                (String) result[1],   // email
                (String) result[2],   // firstName
                (String) result[3]    // lastName
        )).collect(Collectors.toList());
    }
    
}
