package com.example.demoDTO2.controller;

import com.example.demoDTO2.dto.SubjectDTO;
import com.example.demoDTO2.entity.Student;
import com.example.demoDTO2.entity.Subject;
import com.example.demoDTO2.repository.StudentRepository;
import com.example.demoDTO2.repository.SubjectRepository;
import com.example.demoDTO2.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @PutMapping("/bulk")
    public ResponseEntity<?> assignStudentsToSubjects(@RequestBody Map<String, List<Long>> body) {
        List<Long> studentIds = body.get("studentIds");
        List<Long> subjectIds = body.get("subjectIds");

        List<Student> students = studentRepository.findAllById(studentIds);
        List<Subject> subjects = subjectRepository.findAllById(subjectIds);

        for (Student student : students) {
            student.getSubjects().addAll(subjects); // gán tất cả các môn
        }

        studentRepository.saveAll(students);

        return ResponseEntity.ok("Gán thành công tất cả sinh viên với tất cả môn học");
    }

    @PostMapping
    public ResponseEntity<SubjectDTO> create(@RequestBody SubjectDTO dto) {
        SubjectDTO created = subjectService.create(dto);
        return ResponseEntity.ok(created);
    }
}

