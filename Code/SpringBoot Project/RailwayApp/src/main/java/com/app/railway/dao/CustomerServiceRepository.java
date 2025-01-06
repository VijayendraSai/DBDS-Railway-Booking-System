package com.app.railway.dao;

import com.app.railway.model.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerServiceRepository extends JpaRepository<CustomerService, Integer> {

    // Method to find all answered questions (where is_answered = true)
    List<CustomerService> findByIsAnsweredTrue();
    
    // Custom query to search for questions based on keyword
    List<CustomerService> findByIsAnsweredTrueAndQuestionContainingIgnoreCase(String keyword);
    
    Optional<CustomerService> findById(int serviceId);
    
    @Query(value = "SELECT question, answer, question_timestamp, is_answered, answer_timestamp " +
            "FROM customer_service WHERE customer_id = :customerId", nativeQuery = true)
    List<Object[]> findQuestionsByCustomerId(int customerId);
    
    // Custom query to fetch unanswered questions (isAnswered = false)
    @Query("SELECT cs FROM CustomerService cs WHERE cs.isAnswered = false ORDER BY cs.questionTimestamp ASC")
    List<CustomerService> findUnansweredQuestions();
    
}
