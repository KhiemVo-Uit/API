package com.example.demoDTO2.controller;

import com.example.demoDTO2.dto.StudentDTO;
import com.example.demoDTO2.dto.StudentWithUserDTO;
import com.example.demoDTO2.entity.Classroom;
import com.example.demoDTO2.entity.Student;
import com.example.demoDTO2.entity.User;
import com.example.demoDTO2.repository.ClassroomRepository;
import com.example.demoDTO2.repository.StudentRepository;
import com.example.demoDTO2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.example.demoDTO2.dto.StudentDTO;
import com.example.demoDTO2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {


    @Autowired
    private StudentService studentService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // Loại bỏ phần kiểm tra người dùng đã đăng nhập
        model.addAttribute("studentWithUserDTO", new StudentWithUserDTO());
        return "register";  // Luôn hiển thị trang đăng ký
    }

    // Đăng ký sinh viên
    @PostMapping("/register")
    public String registerStudent(@ModelAttribute StudentWithUserDTO dto, Model model) {
        if (dto.getUser() == null || dto.getStudent() == null) {
            model.addAttribute("error", "User and Student info are required!");
            return "register";  // Quay lại trang đăng ký nếu có lỗi
        }

        // Kiểm tra xem username đã tồn tại chưa
        if (userRepository.findByUsername(dto.getUser().getUsername()).isPresent()) {
            model.addAttribute("error", "Username already exists");
            return "register";  // Quay lại trang đăng ký nếu username đã tồn tại
        }

        // Tạo mới đối tượng User và Student
        User user = new User();
        user.setUsername(dto.getUser().getUsername());
        user.setPassword(passwordEncoder.encode(dto.getUser().getPassword()));
        user.setRole(dto.getUser().getRole());

        Student student = new Student();
        student.setName(dto.getStudent().getName());

        // Thiết lập quan hệ giữa User và Student
        student.setUser(user);
        user.setStudent(student);

        userRepository.save(user);  // Lưu user, cascade sẽ tự lưu student

        return "redirect:/login";  // Điều hướng về danh sách sinh viên sau khi đăng ký thành công
    }


    // Lấy tất cả sinh viên
    @GetMapping("/students")
    @ResponseBody
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Lấy sinh viên theo ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        StudentDTO studentDTO = studentService.getStudentById(id);
        return ResponseEntity.ok(studentDTO);
    }

    // Lưu sinh viên mới
    @PostMapping
    public ResponseEntity<StudentDTO> saveStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO savedStudent = studentService.saveStudent(studentDTO);
        return ResponseEntity.ok(savedStudent);
    }

    // Cập nhật sinh viên
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
        StudentDTO updatedStudent = studentService.updateStudent(id, studentDTO);
        return ResponseEntity.ok(updatedStudent);
    }

    // Xóa sinh viên
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}



