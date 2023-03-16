package com.jtc.jtcaccountstudentloan.student.DtoObject;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudentLoanDto {
    private String studentId;
    private Double totalAmountPaid;
    private Double totalLoan;
    private String email;
}
