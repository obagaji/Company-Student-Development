package com.jtc.jtcaccountstudentloan.company.Entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@NoArgsConstructor
@Data
@Builder
@Table(name = "companyloandb")
public class CompanyLoan {
    @Id
    private long staffId;
    @Nonnull
    private String staffName;
    @Nonnull
    private String email;
    @Nonnull
    private String password;
    private String roles;

    public CompanyLoan(long staffId, String staffName, String email, String password, String roles) {
        this.staffId = staffId;
        this.staffName = staffName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

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
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRoles() {
        return roles;
    }
    public void setRoles(String roles) {
        this.roles = roles;
    }
}
