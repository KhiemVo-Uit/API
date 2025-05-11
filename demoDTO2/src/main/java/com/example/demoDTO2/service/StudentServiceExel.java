package com.example.demoDTO2.service;

import com.example.demoDTO2.dto.StudentDtoExel;
import com.example.demoDTO2.dto.UserDTO;
import com.example.demoDTO2.entity.Student;
import com.example.demoDTO2.entity.User;
import com.example.demoDTO2.repository.StudentRepository;
import com.example.demoDTO2.repository.UserRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceExel {
    private UserRepository userRepository;

    private StudentRepository studentRepository;


    private BCryptPasswordEncoder passwordEncoder;  // Inject BCryptPasswordEncoder

    @Autowired
    public StudentServiceExel(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, StudentRepository studentRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.studentRepository = studentRepository;
    }

    public List<StudentDtoExel> readStudentsFromExcel(MultipartFile file) throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        List<StudentDtoExel> students = new ArrayList<>();

        for (Row row : sheet) {
            if (row.getRowNum() == 0) continue;

            StudentDtoExel studentDto = new StudentDtoExel();
            UserDTO userDto = new UserDTO();

            studentDto.setName(row.getCell(0).getStringCellValue());
            userDto.setUsername(row.getCell(1).getStringCellValue());

            // Mã hóa mật khẩu trước khi lưu
            String rawPassword = row.getCell(2).getStringCellValue();
            String encodedPassword = passwordEncoder.encode(rawPassword);  // Mã hóa mật khẩu
            userDto.setPassword(encodedPassword);

            userDto.setRole("STUDENT");

            studentDto.setUser(userDto);
            students.add(studentDto);
        }

        workbook.close();
        return students;
    }

    public void saveStudents(List<StudentDtoExel> students) {
        for (StudentDtoExel studentDto : students) {
            // Tạo đối tượng User từ UserDTO
            User user = new User();
            user.setUsername(studentDto.getUser().getUsername());
            user.setPassword(studentDto.getUser().getPassword()); // Đảm bảo mật khẩu đã được mã hóa
            user.setRole(studentDto.getUser().getRole());

            // Lưu User vào DB
            userRepository.save(user);

            // Tạo đối tượng Student và liên kết với User
            Student student = new Student();
            student.setName(studentDto.getName());
            student.setUser(user);  // Liên kết Student với User đã lưu

            // Lưu Student vào DB
            studentRepository.save(student);
        }
    }
}
