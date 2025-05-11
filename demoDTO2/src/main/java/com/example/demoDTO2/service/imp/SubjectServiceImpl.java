package com.example.demoDTO2.service.imp;

import com.example.demoDTO2.dto.SubjectDTO;
import com.example.demoDTO2.entity.Subject;
import com.example.demoDTO2.repository.SubjectRepository;
import com.example.demoDTO2.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public SubjectDTO create(SubjectDTO dto) {
        Subject subject = new Subject();
        subject.setName(dto.getName());
        Subject saved = subjectRepository.save(subject);

        SubjectDTO result = new SubjectDTO();
        result.setId(saved.getId());
        result.setName(saved.getName());
        return result;
    }
}

