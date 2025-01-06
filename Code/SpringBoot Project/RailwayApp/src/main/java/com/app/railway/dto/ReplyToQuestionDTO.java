package com.app.railway.dto;

public class ReplyToQuestionDTO {
    private int serviceId;
    private String answer;
    
    public ReplyToQuestionDTO() {}
    
	public ReplyToQuestionDTO(int serviceId, String answer) {
		super();
		this.serviceId = serviceId;
		this.answer = answer;
	}
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
    
    
}
