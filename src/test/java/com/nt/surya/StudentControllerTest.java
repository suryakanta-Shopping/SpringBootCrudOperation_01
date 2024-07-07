package com.nt.surya;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.nt.surya.controller.StudentController;
import com.nt.surya.entity.Student;
import com.nt.surya.service.IStudentService;

class StudentControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private StudentController studentController;

    @Mock
    private IStudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    void createStudent() throws Exception {
        Student student = new Student();
        student.setSname("John");
        student.setSaddress("123 Main St");

        when(studentService.saveStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(post("/api/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"sname\":\"John\", \"saddress\":\"123 Main St\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sname").value("John"))
                .andExpect(jsonPath("$.saddress").value("123 Main St"));
    }

    @Test
    void updateStudent() throws Exception {
        Student student = new Student();
        student.setSid(1);
        student.setSname("John");
        student.setSaddress("123 Main St");

        when(studentService.updateStudent(any(Student.class))).thenReturn(student);

        mockMvc.perform(put("/api/students/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"sname\":\"John\", \"saddress\":\"123 Main St\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sname").value("John"))
                .andExpect(jsonPath("$.saddress").value("123 Main St"));
    }

    @Test
    void getStudentById() throws Exception {
        Student student = new Student();
        student.setSid(1);
        student.setSname("John");
        student.setSaddress("123 Main St");

        when(studentService.getStudentById(any())).thenReturn(student);

        mockMvc.perform(get("/api/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sname").value("John"))
                .andExpect(jsonPath("$.saddress").value("123 Main St"));
    }

    @Test
    void getAllStudents() throws Exception {
        Student student1 = new Student();
        student1.setSname("John");
        student1.setSaddress("123 Main St");

        Student student2 = new Student();
        student2.setSname("Jane");
        student2.setSaddress("456 Oak St");

        List<Student> students = Arrays.asList(student1, student2);

        when(studentService.getAllStudents()).thenReturn(students);

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].sname").value("John"))
                .andExpect(jsonPath("$[1].sname").value("Jane"));
    }

    @Test
    void deleteStudent() throws Exception {
        mockMvc.perform(delete("/api/students/1"))
                .andExpect(status().isNoContent());
    }
}
