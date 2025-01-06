package com.app.railway.dto;

import java.time.LocalDateTime;

public class UnansweredQuestionDTO {

    private int serviceId;
    private int customerId;
    private String question;
    private LocalDateTime questionTimestamp;
    
    // Constructor, getters, and setters
    public UnansweredQuestionDTO() {}
    
	public UnansweredQuestionDTO(int serviceId, int customerId, String question, LocalDateTime questionTimestamp) {
		super();
		this.serviceId = serviceId;
		this.customerId = customerId;
		this.question = question;
		this.questionTimestamp = questionTimestamp;
	}
	
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public LocalDateTime getQuestionTimestamp() {
		return questionTimestamp;
	}
	public void setQuestionTimestamp(LocalDateTime questionTimestamp) {
		this.questionTimestamp = questionTimestamp;
	}
    
}
