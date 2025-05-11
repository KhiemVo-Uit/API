package com.example.demoDTO2.controller;

import com.example.demoDTO2.dto.StudentDtoExel;
import com.example.demoDTO2.service.StudentServiceExel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor  // từ lombok
public class StudentImportController {

    @Autowired
    private  StudentServiceExel studentServiceExel;

    @PostMapping("/import")
    public ResponseEntity<?> importStudents(@RequestParam("file") MultipartFile file) throws IOException {
        // Đọc dữ liệu từ file Excel và mã hóa mật khẩu
        List<StudentDtoExel> students = studentServiceExel.readStudentsFromExcel(file);

        // Lưu vào cơ sở dữ liệu (nếu cần)
        studentServiceExel.saveStudents(students);

        return ResponseEntity.ok("Import thành công!");
    }

}

