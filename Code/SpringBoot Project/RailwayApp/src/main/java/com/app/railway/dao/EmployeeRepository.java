package com.app.railway.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.railway.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	List<Employee> findByRole(Employee.Role role);

    // Custom query to find customer by email (if needed)
	Employee findByEmail(String email);

    // Custom query to find customer by username (if needed)
	Employee findByUsername(String username);
}
