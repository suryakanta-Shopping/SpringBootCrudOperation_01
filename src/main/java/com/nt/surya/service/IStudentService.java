package com.nt.surya.service;

import java.util.List;

import com.nt.surya.entity.Student;

public interface IStudentService {

	Student saveStudent(Student student);

	List<Student> getAllStudents();

	Student getStudentById(Integer sid);

	Student updateStudent(Student student);

	void deleteStudent(Integer sid);
}
