package com.example.demoDTO2.service;




import com.example.demoDTO2.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    // Lấy tất cả sinh viên
    List<StudentDTO> getAllStudents();

    // Lấy sinh viên theo ID
    StudentDTO getStudentById(Long id);

    // Lưu sinh viên
    StudentDTO saveStudent(StudentDTO studentDTO);

    // Cập nhật sinh viên
    StudentDTO updateStudent(Long id, StudentDTO studentDTO);

    // Xóa sinh viên
    void deleteStudent(Long id);
}


