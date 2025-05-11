package com.example.demoDTO2.service.imp;


import com.example.demoDTO2.dto.StudentDTO;
import com.example.demoDTO2.entity.Student;
import com.example.demoDTO2.map.StudentMapper;
import com.example.demoDTO2.repository.StudentRepository;
import com.example.demoDTO2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    public Long checkNextId() {
        long count = studentRepository.count();
        long nextId = count + 1;
        return nextId;
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return studentMapper.toDto(student);
    }

    @Override
    public StudentDTO saveStudent(StudentDTO studentDTO) {
        studentDTO.setId(checkNextId());
        Student student = studentMapper.toEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDto(savedStudent);
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setName(studentDTO.getName());
        student.setName(studentDTO.getClassroomName());

        // Cập nhật các môn học, v.v. theo yêu cầu

        Student updatedStudent = studentRepository.save(student);
        return studentMapper.toDto(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}

