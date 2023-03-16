package com.jtc.jtcaccountstudentloan.student.service;


import com.jtc.jtcaccountstudentloan.company.exceptionManager.InputDataInvalidException;
import com.jtc.jtcaccountstudentloan.mapper.CompanyStudentMapper;
import com.jtc.jtcaccountstudentloan.student.DtoObject.StudentDtoResponse;
import com.jtc.jtcaccountstudentloan.student.DtoObject.StudentLoanDto;
import com.jtc.jtcaccountstudentloan.student.DtoObject.StudentLoginDto;
import com.jtc.jtcaccountstudentloan.student.entity.Students;
import com.jtc.jtcaccountstudentloan.student.repository.StudentRepository;
import com.jtc.jtcaccountstudentloan.StudentException.NoStudentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    CompanyStudentMapper companyStudentMapper;

    @Autowired
    Students students;

    public StudentService(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }

    @PreAuthorize("isAuthenticated() and hasRole('admin)")
    public Students saveStudentInfo(Students saveStudent)
    {
       return  studentRepository.save(saveStudent);
    }
    /*
    * StudentloginDto is the parameter that is used for the method login.
    * StudentLogin has two variables username and password.
    * The Repository method returns an optional of Students class.
    * The return type of this method is a Students.
    * */
    public Students loginStudent(StudentLoginDto studentLoginDto) throws NoStudentException {
        Students studentLogin = studentRepository.
                findByUsernameAndPassword(studentLoginDto.getUsername(),studentLoginDto.getPassword()).
                orElseThrow(NoStudentException::new);
        return studentLogin;
    }
    // method getTotalLoanCollected takes a parameter of type long
    // it throws a custom exception that implement supplier interface
    //the supplier interface has a return type of class Students
    // the method is expected to return a value of type double.
    //this method is to be called in the controller class and the exception must be caught
    public double getTotalStudentLoanCollected(Long studentId) throws NoStudentException
    {
        Students student = studentRepository.findById(studentId).orElseThrow(NoStudentException::new);
        return student.getTotalLoan();
    }
    /* The setLoanAmountPaid method returns a Students class object.
    the method is expected to take two parameters, a double and a Long
    the method  throws an unchecked custom exception, of the type NoStudentException
    this exception extends java  Exception class and implements Suppliers interface.
    This method call the findById method of the JPARepository, which has an orElseThrow method.
    The return students object from the findById method is used to update the Amount variable;
    * */

    public Students setLoanAmountPaid(double amount, Long studentId)throws NoStudentException
    {
        Students newLoanStudent = new Students();
        Students loadStudent = studentRepository.findById(studentId).orElseThrow(NoStudentException::new);
        if (amount<0){
            throw new InputDataInvalidException("Enter Value greater than 0", "invalid input", HttpStatus.BAD_REQUEST);
        }
       newLoanStudent.setStudentId(loadStudent.getStudentId());
       newLoanStudent.setStudentName(loadStudent.getStudentName());
       //newLoanStudent.setAmountPaid(loadStudent.getAmountPaid());
       newLoanStudent.setTotalLoan(amount);
       newLoanStudent.setTotalAmountPaid(loadStudent.getTotalAmountPaid());
        return studentRepository.save(newLoanStudent);
    }
    public List<StudentLoanDto> getAllListWithStaffId(long staffId)
    {
        List<Students> listStudentToStaff = studentRepository.findByStaffId(staffId);
        return listStudentToStaff.stream().map(companyStudentMapper::studentModelToDto)
                .collect(Collectors.toList());
    }
    /*
    * get a list of all the student registered with the company
    *
    *
    * */
    public List<Students> getAllStudentLoanData(int number, int recordNumber)
    {
        List<Students> studentsList = new ArrayList<>();
        Pageable pageable = PageRequest.of(number, recordNumber,Sort.by("studentName"));
        if (recordNumber==0 || recordNumber< 0)
        {
            studentsList = studentRepository.findAll();
            recordNumber = studentsList.size();
        }
        else {
            studentsList = studentRepository.findAll(pageable).get().toList();
        }
        return studentsList;
    }
    public StudentDtoResponse getInfoAmountRemaining( String studentId, String email)
    {
        StudentDtoResponse studentDtoResponse = studentRepository.getTotalLoan(studentId, email);
        return studentDtoResponse;
    }


}
