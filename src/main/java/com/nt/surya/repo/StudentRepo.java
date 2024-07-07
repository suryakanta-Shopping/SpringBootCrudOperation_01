package com.nt.surya.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.surya.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

}
