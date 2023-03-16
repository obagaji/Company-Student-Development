package com.jtc.jtcaccountstudentloan.student.repository;

import com.jtc.jtcaccountstudentloan.student.DtoObject.StudentDtoResponse;
import com.jtc.jtcaccountstudentloan.student.DtoObject.StudentLoanDto;
import com.jtc.jtcaccountstudentloan.student.DtoObject.StudentLoginDto;
import com.jtc.jtcaccountstudentloan.student.entity.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Students, Long> {


    @Query( "SELECT s.totalLoan,s.totalAmountPaid FROM STUDENTS s WHERE s.studentId = : studentId and s.email = : email")
    public StudentDtoResponse getTotalLoan(String studentId, String email);

    Optional<Students> findByUsernameAndPassword(String username, String password);

    public List<Students> findByStaffId(long staffId);

    Optional<Students> findByUsername(String  username);
}
