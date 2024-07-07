package com.nt.surya.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.surya.entity.Student;
import com.nt.surya.service.IStudentService;

@RestController
//@Controller
@RequestMapping("/std")
public class StudentController {

	@Autowired
	private IStudentService service;

	@PostMapping("/save")
	public Student SaveStudent(@RequestBody Student student) 
	{
		return service.saveStudent(student);
	}

	@GetMapping("/show")
	public List<Student> showStudent() 
	{
		return service.getAllStudents();
	}

	@GetMapping("/{id}")
	public Student getStudentId(
			@PathVariable Integer sid)
	{
		return service.getStudentById(sid);
	}

	@PutMapping("/{id}")
	public Student updateStudent(
			@PathVariable Integer sid,
			@RequestBody Student student)
	{
		student.setSid(sid);
		return service.updateStudent(student);
	}

	@DeleteMapping("/{id}")
	public void deleteStudent(@PathVariable Integer sid) {
		service.deleteStudent(sid);

	}
}
