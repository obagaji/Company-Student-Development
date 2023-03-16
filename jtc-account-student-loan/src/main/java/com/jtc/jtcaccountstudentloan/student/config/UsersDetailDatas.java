package com.jtc.jtcaccountstudentloan.student.config;

import com.jtc.jtcaccountstudentloan.student.entity.Students;
import com.jtc.jtcaccountstudentloan.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UsersDetailDatas implements UserDetailsService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Students> students = studentRepository.findByUsername(username);
        return students.map(StudentCompanyUser::new).orElseThrow(()->new UsernameNotFoundException("Username not found"));
    }
}
