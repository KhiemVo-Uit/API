package com.example.demoDTO2.controller;

import com.example.demoDTO2.entity.Classroom;
import com.example.demoDTO2.repository.ClassroomRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api/classrooms")
public class ClassroomController {

    private final ClassroomRepository classroomRepository;

    public ClassroomController(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @PostMapping
    public ResponseEntity<Classroom> create(@RequestBody Classroom classroom) {
        Classroom saved = classroomRepository.save(classroom);
        return ResponseEntity.ok(saved);
    }
}

