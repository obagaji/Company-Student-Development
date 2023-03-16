package com.jtc.jtcaccountstudentloan.StudentException;

import com.jtc.jtcaccountstudentloan.student.entity.Students;


import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.function.Supplier;

public class NoStudentException extends Exception implements Supplier<Students> {

    String [] noValue = {"no value"};
    public NoStudentException()
    {
        super("No value Found");
    }

    //LocalDateTime.now(),

    @Override
    public Students get() {
        return new Students(0L,"Does Not exist",0,
                0,"No value","No value",0L,noValue);
    }
}
