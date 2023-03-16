package com.jtc.jtcaccountstudentloan.student.Controller;
import com.jtc.jtcaccountstudentloan.StudentException.NoStudentException;
import com.jtc.jtcaccountstudentloan.company.exceptionManager.EmptyFieldException;
import com.jtc.jtcaccountstudentloan.student.DtoObject.StudentLoginDto;
import com.jtc.jtcaccountstudentloan.student.entity.Students;
import com.jtc.jtcaccountstudentloan.student.service.StudentService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
@Component
@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
 /* StudentRepository is injected by the use of constructor injection
 * to achieve dependency injection a constructor is created for StudentController class
 * the parameter of the constructor is a repository object
 * */
    public StudentController(StudentService studentService)
    {
        this.studentService = studentService;
    }

/*
* Post Mapping to save the student into the database.
* Its takes as parameter the students object, uri as /sava-student
* its returns a students object
*
* Although this method does not explicit, return a ResponseEntity, but the HttpStatus
* upon completion of the method request is Ok.
* */
    @PostMapping("/save-student")
    public ResponseEntity<Students> saveStudentInfo(@RequestBody Students studentBody)
    {
      return new ResponseEntity<> (studentService.saveStudentInfo(studentBody),HttpStatus.CREATED);
    }
    /*
    * A post request method for user to be able to login
    * There is no authentication requirement for this interface.
    * So every body show be able to access this page.
    *  They parameters that will be required are the username, password,
    * */
    @PostMapping("/login")
    public ResponseEntity<Students> loginInstanceForStudent(@RequestBody StudentLoginDto studentLoginDto) throws NoStudentException {
        Students students =  studentService.loginStudent(studentLoginDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/login").build(students);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(students);
    }
    /*
    * The get parameters are the number and the record Number
    * Returning a response Entity
    *
    * Returning a 200 ok status and the content type is Application_JSON
    *
    * */
    @GetMapping("/all/students/{number}/{recordNumber}")
    public ResponseEntity<List<Students>> getAllStudent(@PathVariable(" number") int number, @PathVariable("recordNumber") int recordNumber)
    {
        List<Students> studentsList =studentService.getAllStudentLoanData(number, recordNumber);
        return ResponseEntity.status(HttpStatus.OK).body(studentsList);
    }
    @GetMapping("/student/{studentId}")
    public ResponseEntity<Double>getStudentWithId(@PathVariable("studentId") Long studentId) throws NoStudentException
    {
        Double studentAmount = studentService.getTotalStudentLoanCollected(studentId);
        return new ResponseEntity<>(studentAmount,HttpStatus.OK);
    }
    @PutMapping("/student/{amount}/{studentId}")
    public ResponseEntity<Students> saveAmountToStudentId(@PathVariable("amount") Double amount,
                                                          @PathVariable("studentId") long studentId) throws  NoStudentException, EmptyFieldException
    {
                Students students = studentService.setLoanAmountPaid(amount, studentId);
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{studentId}").build(students);
                HttpHeaders header = new HttpHeaders();
                header.setLocation(uri);
                return  ResponseEntity.status(HttpStatus.CREATED).headers(header).build();
    }
}
