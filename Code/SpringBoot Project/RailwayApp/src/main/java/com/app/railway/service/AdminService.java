package com.app.railway.service;

import com.app.railway.model.Employee;
import com.app.railway.dao.EmployeeRepository;
import com.app.railway.dao.ReservationRepository;
import com.app.railway.dto.ActiveTransitLineDto;
import com.app.railway.dto.ReservationByEmailDto;
import com.app.railway.dto.ReservationByTransitLineDto;
import com.app.railway.dto.RevenueByCustomerDto;
import com.app.railway.dto.RevenueByTransitLineDto;
import com.app.railway.dto.SalesReportDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    ReservationRepository reservationRepository;

    // Add a new representative
    public Employee addRepresentative(Employee representative) {
        return employeeRepository.save(representative);
    }

    // Update representative details
    public Employee updateRepresentative(Long id, Employee updatedDetails) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isEmpty()) {
            throw new IllegalArgumentException("Representative not found with ID: " + id);
        }

        Employee employee = existingEmployee.get();

        // Update only non-null fields
        if (updatedDetails.getFirstName() != "") {
            employee.setFirstName(updatedDetails.getFirstName());
        }
        if (updatedDetails.getLastName() != "") {
            employee.setLastName(updatedDetails.getLastName());
        }
        if (updatedDetails.getEmail() != "") {
            employee.setEmail(updatedDetails.getEmail());
        }
        if (updatedDetails.getUsername() != "") {
            employee.setUsername(updatedDetails.getUsername());
        }
        if (updatedDetails.getPassword() != "") { // Consider password hashing
            employee.setPassword(updatedDetails.getPassword());
        }
        if (updatedDetails.getSsn() != "") {
            employee.setSsn(updatedDetails.getSsn());
        }

        // Save and return the updated employee
        return employeeRepository.save(employee);
    }


    // Delete a representative
    public void deleteRepresentative(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new IllegalArgumentException("Representative not found with ID: " + id);
        }
        employeeRepository.deleteById(id);
    }
    
    // Get all customer representatives
    public List<Employee> getRepresentatives() {
        return employeeRepository.findByRole(Employee.Role.REPRESENTATIVE);
    }
    
    public List<SalesReportDto> getMonthlySalesReport() {
        return reservationRepository.getMonthlySalesReport();
    }
    
    public List<ReservationByTransitLineDto> getReservationsByTransitLine(String transitLineName) {
        return reservationRepository.findReservationsByTransitLineName(transitLineName);
    }
    
    public List<ReservationByEmailDto> getReservationsByCustomerEmail(String email) {
        return reservationRepository.findReservationsByCustomerEmail(email);
    }
    
    public List<RevenueByTransitLineDto> getRevenueByTransitLine() {
        return reservationRepository.findRevenueByTransitLine();
    }
    
    public List<RevenueByCustomerDto> getRevenuePerCustomer() {
        return reservationRepository.findRevenueByCustomerEmail();
    }
    
    public RevenueByCustomerDto getBestCustomer() {
        List<RevenueByCustomerDto> revenueList = reservationRepository.findRevenueByCustomerEmail();
        if (!revenueList.isEmpty()) {
            return revenueList.get(0);  // return the first entry (best customer)
        }
        return null;  // return null if no customer found
    }
    
    public List<ActiveTransitLineDto> getTop5ActiveTransitLines() {
        List<Object[]> results = reservationRepository.findTop5ActiveTransitLinesNative();
        List<ActiveTransitLineDto> transitLines = new ArrayList<>();
        for (Object[] result : results) {
            String transitLineName = (String) result[0];
            long reservationCount = ((Number) result[1]).longValue();
            transitLines.add(new ActiveTransitLineDto(transitLineName, reservationCount));
        }
        return transitLines;
    }


}
