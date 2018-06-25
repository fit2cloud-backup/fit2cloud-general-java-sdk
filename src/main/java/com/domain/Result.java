package com.domain;

public class Result {
    private Boolean resultCode ;
    private String message;

    public Boolean getResultCode() {
        return resultCode;
    }

    public void setResultCode(Boolean resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultCode=" + resultCode +
                ", message='" + message + '\'' +
                '}';
    }
}
