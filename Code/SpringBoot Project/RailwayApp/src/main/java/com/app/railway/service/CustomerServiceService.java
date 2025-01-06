package com.app.railway.service;

import com.app.railway.dao.CustomerServiceRepository;
import com.app.railway.dto.AnsweredQuestionDTO;
import com.app.railway.dto.CustomerQuestionDTO;
import com.app.railway.dto.CustomerServiceRequestDTO;
import com.app.railway.dto.ReplyToQuestionDTO;
import com.app.railway.dto.UnansweredQuestionDTO;
import com.app.railway.model.Customer;
import com.app.railway.model.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceService {

    @Autowired
    private CustomerServiceRepository customerServiceRepository;

    // Method to get all answered questions (where is_answered = true)
    public List<AnsweredQuestionDTO> getAllAnsweredQuestions() {
        List<CustomerService> answeredQuestions = customerServiceRepository.findByIsAnsweredTrue();
        return answeredQuestions.stream()
                .map(question -> new AnsweredQuestionDTO(question.getQuestion(), question.getAnswer()))
                .collect(Collectors.toList());
    }
    
    // Method to search questions by keyword
    public List<AnsweredQuestionDTO> searchAnsweredQuestionsByKeyword(String keyword) {
        List<CustomerService> answeredQuestions = customerServiceRepository.findByIsAnsweredTrueAndQuestionContainingIgnoreCase(keyword);
        return answeredQuestions.stream()
                .map(question -> new AnsweredQuestionDTO(question.getQuestion(), question.getAnswer()))
                .collect(Collectors.toList());
    }
    
    // Method to handle customer question submission
    public void sendQuestion(CustomerServiceRequestDTO requestDTO) {
        // Create a new CustomerService entity
        CustomerService customerService = new CustomerService();
        Customer customer = new Customer();
        customer.setCustomerId(requestDTO.getCustomerId());
        customerService.setCustomer(customer);
        customerService.setQuestion(requestDTO.getQuestion());
        customerService.setQuestionTimestamp(LocalDateTime.now());
        customerService.setAnswered(false);

        // Save the entity to the database
        customerServiceRepository.save(customerService);
    }
    
    // Method for replying to a customer question
    public void replyToQuestion(ReplyToQuestionDTO replyToQuestionDTO) {
        // Retrieve the CustomerService entity by ID
        Optional<CustomerService> customerServiceOptional = customerServiceRepository.findById(replyToQuestionDTO.getServiceId());

        if (customerServiceOptional.isPresent()) {
            CustomerService customerService = customerServiceOptional.get();

            // Update fields with the representative's reply
            customerService.setAnswer(replyToQuestionDTO.getAnswer());
            customerService.setAnswerTimestamp(LocalDateTime.now());
            customerService.setAnswered(true);

            // Save the updated entity
            customerServiceRepository.save(customerService);
        } else {
            throw new IllegalArgumentException("Service ID not found: " + replyToQuestionDTO.getServiceId());
        }
    }
    
    public List<CustomerQuestionDTO> getQuestionsByCustomerId(int customerId) {
        List<Object[]> results = customerServiceRepository.findQuestionsByCustomerId(customerId);
        return results.stream().map(result -> new CustomerQuestionDTO(
                (String) result[0],  // question
                (String) result[1],  // answer
                result[2] != null ? ((Timestamp) result[2]).toLocalDateTime() : null,  // questionTimestamp
                (Boolean) result[3],  // isAnswered
                result[4] != null ? ((Timestamp) result[4]).toLocalDateTime() : null   // answerTimestamp
        )).collect(Collectors.toList());
    }
    
    // Get all unanswered questions
    public List<UnansweredQuestionDTO> getAllUnansweredQuestions() {
        List<CustomerService> customerServices = customerServiceRepository.findUnansweredQuestions();

        return customerServices.stream().map(cs -> {
            UnansweredQuestionDTO dto = new UnansweredQuestionDTO();
            dto.setServiceId(cs.getServiceId());
            dto.setCustomerId(cs.getCustomer().getCustomerId());
            dto.setQuestion(cs.getQuestion());
            dto.setQuestionTimestamp(cs.getQuestionTimestamp());
            return dto;
        }).collect(Collectors.toList());
    }
}
