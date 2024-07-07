package com.nt.surya;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.nt.surya.entity.Student;
import com.nt.surya.repo.StudentRepo;
import com.nt.surya.service.impl.StudentServiceImpl;

class StudentServiceImplTest {

	@InjectMocks
	private StudentServiceImpl studentService;

	@Mock
	private StudentRepo studentRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void createStudent() {
		Student student = new Student();
	//	student.setSid(2);
		student.setSname("John");
		student.setSaddress("123 Main St");

		when(studentRepository.save(any(Student.class))).thenReturn(student);

		Student createdStudent = studentService.saveStudent(student);

		assertNotNull(createdStudent);
		assertEquals("John", createdStudent.getSname());
	}

	@Test
	void updateStudent() {
		Student student = new Student();
		student.setSid(1);
		student.setSname("John");
		student.setSaddress("123 Main St");

		when(studentRepository.save(any(Student.class))).thenReturn(student);

		Student updatedStudent = studentService.updateStudent(student);

		assertNotNull(updatedStudent);
		assertEquals("John", updatedStudent.getSname());
	}

	@Test
	void getStudentById() {
		Student student = new Student();
		student.setSid(1);
		student.setSname("John");
		student.setSaddress("123 Main St");

		when(studentRepository.findById(1)).thenReturn(Optional.of(student));

		Student foundStudent = studentService.getStudentById(1);

		assertNotNull(foundStudent);
		assertEquals("John", foundStudent.getSname());
	}

	@Test
	void getAllStudents() {
		Student student1 = new Student();
		student1.setSname("John");
		student1.setSaddress("123 Main St");

		Student student2 = new Student();
		student2.setSname("Jane");
		student2.setSaddress("456 Oak St");

		List<Student> students = Arrays.asList(student1, student2);

		when(studentRepository.findAll()).thenReturn(students);

		List<Student> allStudents = studentService.getAllStudents();

		assertNotNull(allStudents);
		assertEquals(2, allStudents.size());
	}

	@Test
	void deleteStudent() {
		doNothing().when(studentRepository).deleteById(1);

		studentService.deleteStudent(1);

		verify(studentRepository, times(1)).deleteById(1);
	}
}
