package com.jtc.jtcaccountstudentloan.mapper;


import com.jtc.jtcaccountstudentloan.company.DtoCompany.CompanyDto;
import com.jtc.jtcaccountstudentloan.company.DtoCompany.CompanyLoginDto;
import com.jtc.jtcaccountstudentloan.company.Entity.CompanyLoan;
import com.jtc.jtcaccountstudentloan.student.DtoObject.StudentDtoResponse;
import com.jtc.jtcaccountstudentloan.student.DtoObject.StudentLoanDto;
import com.jtc.jtcaccountstudentloan.student.entity.Students;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface CompanyStudentMapper {

  /*
  * It important that the naming is taken into consideration before we name our DTO and Classes
  *
  * */
    CompanyStudentMapper INSTANCE = Mappers.getMapper(CompanyStudentMapper.class);
    CompanyDto   companyModelToDto(CompanyLoan companyLoan);
    CompanyLoan dtoToMCompanyModel(CompanyDto companyDto);

    Students  dtoToStudentModel(StudentLoanDto studentLoanDto);
    StudentLoanDto studentModelToDto(Students students);

    Students dtoModelTo (StudentDtoResponse studentDtoResponse);

    CompanyLoan dtoToCompanyLogin(CompanyLoginDto companyLoginDto);
    CompanyLoginDto CompanyloginDto(CompanyLoan companyLoan);

    
}
