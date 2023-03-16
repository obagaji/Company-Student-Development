package com.jtc.jtcaccountstudentloan.student.DtoObject;


import org.springframework.stereotype.Component;

@Component
public class StudentLoginDto {


    private String username;
    private String password;

    public StudentLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public StudentLoginDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
