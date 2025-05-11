package com.example.demoDTO2.repository;


import com.example.demoDTO2.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}

