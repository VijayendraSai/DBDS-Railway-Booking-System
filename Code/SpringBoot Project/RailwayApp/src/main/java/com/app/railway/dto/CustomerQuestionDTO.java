package com.app.railway.dto;

import java.time.LocalDateTime;

public class CustomerQuestionDTO {

    private String question;
    private String answer;
    private LocalDateTime questionTimestamp;
    private boolean isAnswered;
    private LocalDateTime answerTimestamp;

    // Constructor
    public CustomerQuestionDTO(String question, String answer, LocalDateTime questionTimestamp, 
                               boolean isAnswered, LocalDateTime answerTimestamp) {
        this.question = question;
        this.answer = answer;
        this.questionTimestamp = questionTimestamp;
        this.isAnswered = isAnswered;
        this.answerTimestamp = answerTimestamp;
    }

    // Getters and Setters
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

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public LocalDateTime getAnswerTimestamp() {
        return answerTimestamp;
    }

    public void setAnswerTimestamp(LocalDateTime answerTimestamp) {
        this.answerTimestamp = answerTimestamp;
    }
}

