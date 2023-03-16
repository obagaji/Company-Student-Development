package com.jtc.jtcaccountstudentloan.company.service;
import com.jtc.jtcaccountstudentloan.company.DtoCompany.CompanyLoginDto;
import com.jtc.jtcaccountstudentloan.company.Entity.CompanyLoan;
import com.jtc.jtcaccountstudentloan.company.Repository.CompanyRepository;
import com.jtc.jtcaccountstudentloan.company.exceptionManager.CompanyNoNameException;
import com.jtc.jtcaccountstudentloan.mapper.CompanyStudentMapper;
import com.jtc.jtcaccountstudentloan.student.DtoObject.StudentLoanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    CompanyStudentMapper companyStudentMapper;


    public CompanyLoginDto loginInToCompany(long staffId,  String staffName)
    {
        CompanyLoan companyLoan = companyRepository.findByStaffIdAndUsername(staffId,staffName)
                .orElseThrow(()-> new CompanyNoNameException());
        return companyStudentMapper.CompanyloginDto(companyLoan);
    }
    public CompanyLoan saveCustomerLoanAmount(CompanyLoan loanCompany)
    {
        return companyRepository.save(loanCompany);
    }
    public List<CompanyLoan> getAllLoanTaken()
    {
       List<CompanyLoan> loanList = companyRepository.findAll();
       return loanList;
    }
    public CompanyLoan getLoanId(long loanId) throws CompanyNoNameException
    {
        CompanyLoan loan = companyRepository.findById(loanId).orElseThrow( CompanyNoNameException::new);
        return loan;
    }
    public List<StudentLoanDto> getAllStudentUnderCompanyLoan(long staffId)
    {
        return null;
    }
    public HashMap<String, String> getAllNamesAndAmount() {
        HashMap<String, String> mapInfo = new HashMap<>();
        List<CompanyLoan>nameAndAmount = companyRepository.findAll();
         List<String> names =nameAndAmount.stream()// use of set so that there wil be no duplicate of names
                .map(e-> e.getStaffName())
                .collect(Collectors.toList());
         List<String> amount = nameAndAmount.stream()
                 .map( d -> d.getEmail())
                 .collect(Collectors.toList());
         //int x = 0;
         for (int x=0; x<names.size(); x++)
         {
             mapInfo.put(names.get(x), amount.get(x));
         }
         return mapInfo;

    }
}
