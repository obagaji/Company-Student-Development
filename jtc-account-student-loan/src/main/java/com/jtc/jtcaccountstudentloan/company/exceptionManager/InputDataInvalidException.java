package com.jtc.jtcaccountstudentloan.company.exceptionManager;

import org.springframework.http.HttpStatus;

public class InputDataInvalidException  extends RuntimeException{

    private String error;
    private HttpStatus status;

    public InputDataInvalidException()
    {
        super();
    }
    public InputDataInvalidException(String error, HttpStatus status) {
        this.error = error;
        this.status = status;
    }

    public InputDataInvalidException(String message, String error, HttpStatus status) {
        super(message);
        this.error = error;
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
