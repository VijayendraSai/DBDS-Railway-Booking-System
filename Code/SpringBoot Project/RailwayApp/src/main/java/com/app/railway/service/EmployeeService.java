package com.app.railway.service;

import com.app.railway.dao.EmployeeRepository;
import com.app.railway.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Validate login for employee
    public Employee validateEmployeeLogin(String username, String password) {
        Employee employee = employeeRepository.findByUsername(username);
        if (employee != null && employee.getPassword().equals(password)) {
            return employee;
        }
        return null;
    }
}
