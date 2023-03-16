package com.jtc.jtcaccountstudentloan.student.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Component
@Entity
/*@AllArgsConstructor
@NoArgsConstructor*/
@Data
public class Students {

    @Id
    private long studentId;
    private String studentName;
    private double totalLoan;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
   // private LocalDateTime amountPaid;
    private double totalAmountPaid;
    @Nonnull
    @Column(name = "email",unique = true)
    private String email;
    private String password;
    private String[] roles;
    private long staffId;

    public Students() {
    }

    public Students(long studentId, String studentName, double totalLoan,
                    double totalAmountPaid, String email, String password, long staffId, String[] roles) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.totalLoan = totalLoan;
    //    this.amountPaid = amountPaid;
        this.totalAmountPaid = totalAmountPaid;
        this.email = email;
        this.password = password;
        this.staffId = staffId;
        this.roles = roles;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getTotalLoan() {
        return totalLoan;
    }

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public void setTotalLoan(double totalLoan) {
        this.totalLoan = totalLoan;
    }
/*
    public LocalDateTime getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(LocalDateTime amountPaid) {
        this.amountPaid = amountPaid;
    }*/

    public double getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public void setTotalAmountPaid(double totalAmountPaid) {
        this.totalAmountPaid = totalAmountPaid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
