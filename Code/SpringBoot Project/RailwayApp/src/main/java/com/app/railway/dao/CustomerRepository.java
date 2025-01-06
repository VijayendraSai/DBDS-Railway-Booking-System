package com.app.railway.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.railway.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // Custom query to find customer by email (if needed)
    Customer findByEmail(String email);

    // Custom query to find customer by username (if needed)
    Customer findByUsername(String username);
}
