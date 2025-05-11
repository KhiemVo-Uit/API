package com.example.demoDTO2.repository;


import com.example.demoDTO2.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
