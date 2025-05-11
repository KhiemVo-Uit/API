package com.example.demoDTO2.controller;

import com.example.demoDTO2.dto.BulkStudentRequest;
import com.example.demoDTO2.entity.Student;
import com.example.demoDTO2.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController2 {

    private  StudentRepository studentRepository;

    @Autowired
    public StudentController2(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Student>> createStudents(@RequestBody BulkStudentRequest request) {
        List<Student> students = request.getStudents().stream().map(dto -> {
            Student s = new Student();
            s.setName(dto.getName());
            return s;
        }).collect(Collectors.toList());

        List<Student> saved = studentRepository.saveAll(students);
        return ResponseEntity.ok(saved);
    }
}

