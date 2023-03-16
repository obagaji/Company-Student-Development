package com.jtc.jtcaccountstudentloan.company.exceptionManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;
import java.util.function.Supplier;

@ControllerAdvice
public class NoSuchLoanDetailsFound extends ResponseEntityExceptionHandler {

 @ExceptionHandler(value = NoSuchElementException.class)
 public ResponseEntity<String> handlingException(NoSuchElementException noSuchElementException)
 {
   return new  ResponseEntity<String>("No Such Data Found in Database, Check Input Information", HttpStatus.NOT_FOUND);
 }


 @ExceptionHandler(EmptyFieldException.class)
 public ResponseEntity<String> emptyField(EmptyFieldException emptyFieldException)
 {
   return new ResponseEntity<>("The Fields are empty", HttpStatus.BAD_REQUEST);
 }

 @ExceptionHandler(InputDataInvalidException.class)
 public ResponseEntity<String>InputDataInvalid(InputDataInvalidException inputDataInvalidException)
 {
  return  new ResponseEntity<>("Invalid Input",HttpStatus.BAD_REQUEST);
 }

 @ExceptionHandler(CompanyNoNameException.class)
 public ResponseEntity<String>noNameFound(CompanyNoNameException companyNoNameException)
 {
  return  new ResponseEntity<>("No Such Data Found in Database, Check Input Information",HttpStatus.BAD_REQUEST);
 }


 }
