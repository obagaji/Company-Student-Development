package com.jtc.jtcaccountstudentloan.company.DtoCompany;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Component
public class CompanyLoginDto {
    private long staffId;
    private String staffName;
    private String roles;

}
