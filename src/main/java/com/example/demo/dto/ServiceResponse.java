package com.example.demo.dto;

public class ServiceResponse {

    private String message;

    private Integer responseCode;

    private Object body;

    public ServiceResponse(String message, Integer responseCode, Object body) {
        this.message = message;
        this.responseCode = responseCode;
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
