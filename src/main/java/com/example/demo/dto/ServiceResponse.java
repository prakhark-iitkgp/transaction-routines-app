package com.example.demo.dto;

public class ServiceResponse {

    private String message;

    private Boolean success;

    private Object body;

    public ServiceResponse(String message, Boolean success, Object body) {
        this.message = message;
        this.success = success;
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
