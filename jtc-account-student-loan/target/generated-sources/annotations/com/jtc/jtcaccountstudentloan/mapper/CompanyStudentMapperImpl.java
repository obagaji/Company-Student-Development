package com.jtc.jtcaccountstudentloan.mapper;

import com.jtc.jtcaccountstudentloan.company.DtoCompany.CompanyDto;
import com.jtc.jtcaccountstudentloan.company.DtoCompany.CompanyLoginDto;
import com.jtc.jtcaccountstudentloan.company.Entity.CompanyLoan;
import com.jtc.jtcaccountstudentloan.student.DtoObject.StudentDtoResponse;
import com.jtc.jtcaccountstudentloan.student.DtoObject.StudentLoanDto;
import com.jtc.jtcaccountstudentloan.student.entity.Students;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-15T04:49:06+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.6 (Oracle Corporation)"
)
@Component
public class CompanyStudentMapperImpl implements CompanyStudentMapper {

    @Override
    public CompanyDto companyModelToDto(CompanyLoan companyLoan) {
        if ( companyLoan == null ) {
            return null;
        }

        CompanyDto companyDto = new CompanyDto();

        companyDto.setStaffId( companyLoan.getStaffId() );
        companyDto.setStaffName( companyLoan.getStaffName() );
        companyDto.setEmail( companyLoan.getEmail() );
        companyDto.setRoles( companyLoan.getRoles() );

        return companyDto;
    }

    @Override
    public CompanyLoan dtoToMCompanyModel(CompanyDto companyDto) {
        if ( companyDto == null ) {
            return null;
        }

        long staffId = 0L;
        String staffName = null;
        String email = null;
        String roles = null;

        staffId = companyDto.getStaffId();
        staffName = companyDto.getStaffName();
        email = companyDto.getEmail();
        roles = companyDto.getRoles();

        String password = null;

        CompanyLoan companyLoan = new CompanyLoan( staffId, staffName, email, password, roles );

        return companyLoan;
    }

    @Override
    public Students dtoToStudentModel(StudentLoanDto studentLoanDto) {
        if ( studentLoanDto == null ) {
            return null;
        }

        Students students = new Students();

        return students;
    }

    @Override
    public StudentLoanDto studentModelToDto(Students students) {
        if ( students == null ) {
            return null;
        }

        StudentLoanDto studentLoanDto = new StudentLoanDto();

        return studentLoanDto;
    }

    @Override
    public Students dtoModelTo(StudentDtoResponse studentDtoResponse) {
        if ( studentDtoResponse == null ) {
            return null;
        }

        Students students = new Students();

        return students;
    }

    @Override
    public CompanyLoan dtoToCompanyLogin(CompanyLoginDto companyLoginDto) {
        if ( companyLoginDto == null ) {
            return null;
        }

        long staffId = 0L;
        String staffName = null;
        String email = null;
        String password = null;
        String roles = null;

        CompanyLoan companyLoan = new CompanyLoan( staffId, staffName, email, password, roles );

        return companyLoan;
    }

    @Override
    public CompanyLoginDto CompanyloginDto(CompanyLoan companyLoan) {
        if ( companyLoan == null ) {
            return null;
        }

        CompanyLoginDto companyLoginDto = new CompanyLoginDto();

        return companyLoginDto;
    }
}
