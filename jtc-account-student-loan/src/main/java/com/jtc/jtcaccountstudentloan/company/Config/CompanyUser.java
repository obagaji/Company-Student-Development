package com.jtc.jtcaccountstudentloan.company.Config;
import com.jtc.jtcaccountstudentloan.company.Entity.CompanyLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyUser  implements UserDetails {

    private long staffId;
    private String staffName;
    private String email;
    private String password;
    private List<GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public CompanyUser(CompanyLoan companyLoan)//CompanyLoan companyLoan)
    {
        this.staffId = companyLoan.getStaffId();
        this.staffName = companyLoan.getStaffName();
        this.email = companyLoan.getEmail();
        this.password = companyLoan.getPassword();
        this.authorities =  List.of(companyLoan.getRoles()).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return staffName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
