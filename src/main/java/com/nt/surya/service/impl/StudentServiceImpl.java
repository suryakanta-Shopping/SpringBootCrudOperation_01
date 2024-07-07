package com.nt.surya.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.surya.entity.Student;
import com.nt.surya.repo.StudentRepo;
import com.nt.surya.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private StudentRepo repo;

	@Override
	public Student saveStudent(Student student) {
		return repo.save(student);
	}

	@Override
	public List<Student> getAllStudents() {
		return repo.findAll();
		
	}

	@Override
	public Student getStudentById(Integer sid) {
		Optional<Student> findById = repo.findById(sid);
		return findById.get();
	}

	@Override
	public Student updateStudent(Student student) {
		Student updatestd = repo.save(student);
		return updatestd;
	}

	@Override
	public void deleteStudent(Integer sid) {
		repo.deleteById(sid);

	}

}
