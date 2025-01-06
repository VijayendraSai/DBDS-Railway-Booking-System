package com.app.railway.controller;

import com.app.railway.dto.ActiveTransitLineDto;
import com.app.railway.dto.ReservationByEmailDto;
import com.app.railway.dto.ReservationByTransitLineDto;
import com.app.railway.dto.RevenueByCustomerDto;
import com.app.railway.dto.RevenueByTransitLineDto;
import com.app.railway.dto.SalesReportDto;
import com.app.railway.model.Employee;
import com.app.railway.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Add a new customer representative
    @PostMapping("/representatives")
    public ResponseEntity<Employee> addRepresentative(@RequestBody Employee representative) {
    	representative.setRole(Employee.Role.REPRESENTATIVE);
        Employee savedRep = adminService.addRepresentative(representative);
        return ResponseEntity.ok(savedRep);
    }

    // Update customer representative details
    @PutMapping("/representatives/{id}")
    public ResponseEntity<Employee> updateRepresentative(@PathVariable Long id, @RequestBody Employee updatedDetails) {
        Employee updatedRep = adminService.updateRepresentative(id, updatedDetails);
        return ResponseEntity.ok(updatedRep);
    }

    // Delete a customer representative
    @DeleteMapping("/representatives/{id}")
    public ResponseEntity<String> deleteRepresentative(@PathVariable Long id) {
        adminService.deleteRepresentative(id);
        return ResponseEntity.ok("Representative deleted successfully");
    }
    
    // List all customer representatives
    @GetMapping("/representatives")
    public ResponseEntity<List<Employee>> getAllRepresentatives() {
        List<Employee> representatives = adminService.getRepresentatives();
        return ResponseEntity.ok(representatives);
    }
    
    @GetMapping("/sales-report")
    public List<SalesReportDto> getSalesReport() {
        return adminService.getMonthlySalesReport();
    }
    
    @GetMapping("/reservations-by-transit-line")
    public List<ReservationByTransitLineDto> getReservationsByTransitLine(@RequestParam String transitLineName) {
        return adminService.getReservationsByTransitLine(transitLineName);
    }
    
    @GetMapping("/reservations-by-customer")
    public List<ReservationByEmailDto> getReservationsByCustomerEmail(@RequestParam("email") String email) {
        return adminService.getReservationsByCustomerEmail(email);
    }
    
    @GetMapping("/revenue-per-transit-line")
    public List<RevenueByTransitLineDto> getRevenuePerTransitLine() {
        return adminService.getRevenueByTransitLine();
    }
    
    @GetMapping("/revenue-per-customer")
    public List<RevenueByCustomerDto> getRevenuePerCustomer() {
        return adminService.getRevenuePerCustomer();
    }
    
    @GetMapping("/best-customer")
    public RevenueByCustomerDto getBestCustomer() {
        return adminService.getBestCustomer();
    }
    
    @GetMapping("/top-5-active-transit-lines")
    public List<ActiveTransitLineDto> getTop5ActiveTransitLines() {
        return adminService.getTop5ActiveTransitLines();
    }
}
