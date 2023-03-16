package com.jtc.jtcaccountstudentloan.company.DtoCompany;


import jakarta.annotation.Nonnull;
import org.springframework.stereotype.Component;

@Component
public class CompanyDto {
    
    private long staffId;
    private String staffName;
    private String email;
    private String roles;

    public long getStaffId() {
        return staffId;
    }

    public void setStaffId(long staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
