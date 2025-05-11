package com.example.demoDTO2.repository;

import com.example.demoDTO2.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
}