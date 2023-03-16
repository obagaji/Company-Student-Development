package com.jtc.jtcaccountstudentloan.student.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jtc.jtcaccountstudentloan.StudentException.NoStudentException;
import com.jtc.jtcaccountstudentloan.student.entity.Students;
import com.jtc.jtcaccountstudentloan.student.service.StudentService;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

@ExtendWith(value= SpringExtension.class)
@WebMvcTest
class StudentControllerTest {
    @MockBean// by using @InjectMocks it could not call the repository bean
    StudentService studentService;
    @Autowired
    MockMvc mockMvc;
    //LocalDateTime.now(),
    @Test
    void saveStudentInfoTest() throws Exception {
        String [] roles ={"admin","user"};
        Students students = new Students(3001L,  "studentName", 100.00,
                50.00, "mlsdanny@yahoo.com","password", 100L, roles);
        Mockito.when(studentService.saveStudentInfo(students)).thenReturn(students);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/student/{studentId}",3001L)
                .content(new ObjectMapper().writeValueAsString(students))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(result -> result.getResponse().setStatus(HttpServletResponse.SC_OK))
                .andReturn();

    }

    @Test
    void getStudentWithId() throws NoStudentException {
        String [] roles ={"admin"};
        Students students = new Students(34001L,  "studentName", 101.00,
                50.00, "mlsdanny@yahoo.com","password", 100L, roles);
        Mockito.when(studentService.getTotalStudentLoanCollected(34001L)).thenReturn(101.0);

    }
 /*   @Test
    void testReturnValue() throws Exception {
        String [] roles ={"admin"};
        Double totalLoad = 101.00;
        Students students = new Students(34001L,  "studentName", 101.00,
                50.00, "mlsdanny@yahoo.com","password", 100L, roles);
        Assertions.assertTrue(totalLoad.equals(studentService.getTotalStudentLoanCollected(34001L)));
    }*/
  
}