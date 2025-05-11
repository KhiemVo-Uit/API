package com.example.demoDTO2.controller;

import com.example.demoDTO2.dto.SubjectDTO;
import com.example.demoDTO2.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public ResponseEntity<SubjectDTO> create(@RequestBody SubjectDTO dto) {
        SubjectDTO created = subjectService.create(dto);
        return ResponseEntity.ok(created);
    }
}

