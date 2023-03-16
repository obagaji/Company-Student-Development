package com.jtc.jtcaccountstudentloan.company.Config;

import com.jtc.jtcaccountstudentloan.company.DtoCompany.CompanyDto;
import com.jtc.jtcaccountstudentloan.company.Entity.CompanyLoan;
import com.jtc.jtcaccountstudentloan.company.Repository.CompanyRepository;
import com.jtc.jtcaccountstudentloan.company.exceptionManager.CompanyNoNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetialsCredential implements UserDetailsService {

    @Autowired
    private  CompanyRepository companyRepository;

    @java.lang.Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CompanyLoan> companyLoan = companyRepository.findByUsername(username);
        return companyLoan.map(CompanyUser::new).orElseThrow(CompanyNoNameException::new);
    }
}
