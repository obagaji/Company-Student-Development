package com.jtc.jtcaccountstudentloan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
@SpringBootApplication( exclude = {DataSourceAutoConfiguration.class},scanBasePackages = {"com.jtc.jtcaccountstudentloan.company.Repository.CompanyRepository","com.jtc.jtcaccountstudentloan.student.repository","com.jtc.jtcaccountstudentloan.GeneralConfig"})
public class JtcAccountStudentLoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(JtcAccountStudentLoanApplication.class, args);
	}

}
