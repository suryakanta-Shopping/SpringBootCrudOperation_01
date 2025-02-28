package com.nt.surya.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nt.surya.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {

}
