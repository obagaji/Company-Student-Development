package com.jtc.jtcaccountstudentloan.company.exceptionManager;


import org.springframework.http.ResponseEntity;

public class EmptyFieldException extends RuntimeException {

    public EmptyFieldException()
    {
        super("Fields are Empty: Please look into it and make correction");
    }


}
