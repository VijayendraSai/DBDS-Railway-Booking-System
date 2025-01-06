package com.app.railway.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_service")
public class CustomerService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serviceId")
    private int serviceId;

    @ManyToOne
    @JoinColumn(name = "CustomerId", referencedColumnName = "customerId")
    private Customer customer;

    @Column(name = "Question", columnDefinition = "TEXT")
    private String question;

    @Column(name = "Answer", columnDefinition = "TEXT")
    private String answer;

    @Column(name = "QuestionTimestamp")
    private LocalDateTime questionTimestamp;

    @Column(name = "AnswerTimestamp")
    private LocalDateTime answerTimestamp;

    @Column(name = "IsAnswered")
    private boolean isAnswered;

    // Getters and Setters

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public LocalDateTime getQuestionTimestamp() {
        return questionTimestamp;
    }

    public void setQuestionTimestamp(LocalDateTime questionTimestamp) {
        this.questionTimestamp = questionTimestamp;
    }

    public LocalDateTime getAnswerTimestamp() {
        return answerTimestamp;
    }

    public void setAnswerTimestamp(LocalDateTime answerTimestamp) {
        this.answerTimestamp = answerTimestamp;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean isAnswered) {
        this.isAnswered = isAnswered;
    }

    @Override
    public String toString() {
        return "CustomerService{" +
                "serviceId=" + serviceId +
                ", customer=" + customer +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", questionTimestamp=" + questionTimestamp +
                ", answerTimestamp=" + answerTimestamp +
                ", isAnswered=" + isAnswered +
                '}';
    }
}

