package com.app.railway.controller;

import com.app.railway.dto.AnsweredQuestionDTO;
import com.app.railway.dto.CustomerQuestionDTO;
import com.app.railway.dto.CustomerServiceRequestDTO;
import com.app.railway.dto.ReplyToQuestionDTO;
import com.app.railway.dto.UnansweredQuestionDTO;
import com.app.railway.service.CustomerServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer-service")
public class CustomerServiceController {

    @Autowired
    private CustomerServiceService customerServiceService;

    // Endpoint to get all answered questions (where is_answered = true)
    @GetMapping("/all-questions")
    public ResponseEntity<List<AnsweredQuestionDTO>> getAllAnsweredQuestions() {
        List<AnsweredQuestionDTO> answeredQuestions = customerServiceService.getAllAnsweredQuestions();
        if (answeredQuestions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(answeredQuestions);
    }
    
    // Endpoint to search answered questions by keyword
    @GetMapping("/search-by-keyword")
    public ResponseEntity<List<AnsweredQuestionDTO>> searchAnsweredQuestionsByKeyword(@RequestParam String keyword) {
        List<AnsweredQuestionDTO> answeredQuestions = customerServiceService.searchAnsweredQuestionsByKeyword(keyword);
        if (answeredQuestions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(answeredQuestions);
    }
    
    // Endpoint for customers to send a question
    @PostMapping("/send-question")
    public ResponseEntity<String> sendQuestion(@RequestBody CustomerServiceRequestDTO requestDTO) {
        // Call the service to handle question submission
        customerServiceService.sendQuestion(requestDTO);

        // Return a success response
        return ResponseEntity.ok("Your question has been submitted successfully.");
    }
    
    // Endpoint for reps to reply to customer questions
    @PutMapping("/reply")
    public ResponseEntity<String> replyToQuestion(@RequestBody ReplyToQuestionDTO replyToQuestionDTO) {
        try {
            customerServiceService.replyToQuestion(replyToQuestionDTO);
            return ResponseEntity.ok("Reply submitted successfully.");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
    
    @GetMapping("/customer-questions")
    public ResponseEntity<List<CustomerQuestionDTO>> getCustomerQuestions(@RequestParam int customerId) {
        List<CustomerQuestionDTO> questions = customerServiceService.getQuestionsByCustomerId(customerId);
        if (questions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(questions);
    }
    
    // Endpoint to get all unanswered questions
    @GetMapping("/unanswered-questions")
    public ResponseEntity<List<UnansweredQuestionDTO>> getUnansweredQuestions() {
        List<UnansweredQuestionDTO> unansweredQuestions = customerServiceService.getAllUnansweredQuestions();
        
        if (unansweredQuestions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(unansweredQuestions);
    }
}
