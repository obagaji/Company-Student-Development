package com.jtc.jtcaccountstudentloan.company.exceptionManager;

import com.jtc.jtcaccountstudentloan.company.Entity.CompanyLoan;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class CompanyNoNameException  extends RuntimeException implements Supplier<CompanyLoan> {
    @Override
    public CompanyLoan get() {
        CompanyLoan companyLoan = new CompanyLoan(0L," No name found in Database"," No Email Found",
                " No Object found", " No information Found");
/*        companyLoan.setEmail(" No Email Found");
        companyLoan.setPassword(" No Object found");
        companyLoan.setStaffId(0L);
        companyLoan.setStaffName(" No name found in Database");
        companyLoan.setRoles(" No information Found");*/

        return companyLoan;
    }
}
