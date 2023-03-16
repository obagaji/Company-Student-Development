package com.jtc.jtcaccountstudentloan.company.Repository;


import com.jtc.jtcaccountstudentloan.company.Entity.CompanyLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Component
@Repository
public interface CompanyRepository extends JpaRepository<CompanyLoan, Long> {
 //   Integer create (String firstname, String surname,String email, String password);
    Optional<CompanyLoan>  findByUsername(String username);
    Optional<CompanyLoan> findByStaffIdAndUsername(long staffId, String username);
}
