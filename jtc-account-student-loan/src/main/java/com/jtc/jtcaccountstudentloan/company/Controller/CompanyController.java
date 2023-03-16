package com.jtc.jtcaccountstudentloan.company.Controller;
import com.jtc.jtcaccountstudentloan.company.DtoCompany.CompanyLoginDto;
import com.jtc.jtcaccountstudentloan.company.Entity.CompanyLoan;
import com.jtc.jtcaccountstudentloan.company.service.CompanyService;
import com.jtc.jtcaccountstudentloan.student.DtoObject.StudentLoanDto;
import com.jtc.jtcaccountstudentloan.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    @Autowired
    StudentService studentService;

    @PostMapping("/login/{staffId}/{name}")
    public ResponseEntity<String> loginIntoCompany
            (@PathVariable("staffId") long staffId, @PathVariable("name") String name)
    {
        CompanyLoginDto companyLoginDto = companyService.loginInToCompany(staffId, name);
        // call a service method that will be use for authentication
        return new ResponseEntity<>("login Successful ", HttpStatus.OK);
    }
    @PostMapping("/loan")
    public ResponseEntity<CompanyLoan> saveLoanRequest(@RequestBody CompanyLoan loanCompany)
    {
         CompanyLoan companyLoan = companyService.saveCustomerLoanAmount(loanCompany);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{staffId}").build(companyLoan);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);
        return new ResponseEntity<CompanyLoan>(companyLoan,HttpStatus.CREATED);
    }
    @GetMapping("/check")
    public ResponseEntity<List<CompanyLoan>> getAllLoanTaken()
    {
        List<CompanyLoan> companyAllLoans = companyService.getAllLoanTaken();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(companyAllLoans);
    }
    @GetMapping("/loan/{loanId}")
    public ResponseEntity<CompanyLoan> getCompanyLoanItem(@PathVariable("loanId") long loanId)
    {
        CompanyLoan loanNeeded = companyService.getLoanId(loanId);
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(loanNeeded);
    }
    @GetMapping("/{staffId}")
    public ResponseEntity<HashMap<CompanyLoan,List<StudentLoanDto>>> getAllStudentAttached(@PathVariable("staffId") long staffId)
    {
        List<StudentLoanDto> listStudentLoan = new ArrayList<>();
        HashMap<CompanyLoan,List<StudentLoanDto>> newHash = new HashMap<>();
        CompanyLoan loanNeeded = companyService.getLoanId(staffId);
        List<StudentLoanDto> studentLoanDto = studentService.getAllListWithStaffId(staffId);
        newHash.put(loanNeeded,studentLoanDto);
        return new ResponseEntity<>(newHash,HttpStatus.OK);
    }
    @GetMapping("/check/names")
    public ResponseEntity<HashMap<String, String>> getNamesWithAmount()
    {
        HashMap<String, String> companyLoanDetails = companyService.getAllNamesAndAmount();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(companyLoanDetails);
    }

}
