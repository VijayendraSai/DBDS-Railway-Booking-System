package com.app.railway.service;

import com.app.railway.dao.CustomerRepository;
import com.app.railway.model.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Register the customer without encrypting the password
    public Customer registerCustomer(Customer customer) {
        // Save the customer to the database
        return customerRepository.save(customer);
    }

    // Check if email or username already exists
    public boolean isEmailOrUsernameTaken(String email, String username) {
        return customerRepository.findByEmail(email) != null || customerRepository.findByUsername(username) != null;
    }
    
    public Customer validateCustomerLogin(String username, String password) {
        Customer customer = customerRepository.findByUsername(username);
        if (customer != null && customer.getPassword().equals(password)) {
            return customer;
        }
        return null;
    }
}
