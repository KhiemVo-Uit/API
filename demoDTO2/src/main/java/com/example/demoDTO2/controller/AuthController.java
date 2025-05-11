package com.example.demoDTO2.controller;


import com.example.demoDTO2.dto.BulkUserRequest;
import com.example.demoDTO2.dto.StudentWithUserDTO;
import com.example.demoDTO2.entity.Student;
import com.example.demoDTO2.entity.User;
import com.example.demoDTO2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @PostMapping("/users/bulk")
    public ResponseEntity<List<User>> createUsers(@RequestBody BulkUserRequest request) {
        List<User> users = request.getUsers().stream().map(dto -> {
            User user = new User();
            user.setUsername(dto.getUsername());

            // Mã hóa mật khẩu
            String encodedPassword = passwordEncoder.encode(dto.getPassword());
            user.setPassword(encodedPassword);

            // Kiểm tra và set role
            if ("STUDENT".equals(dto.getRole()) || "TEACHER".equals(dto.getRole()) || "ADMIN".equals(dto.getRole())) {
                user.setRole(dto.getRole());
            } else {
                throw new IllegalArgumentException("Invalid role: " + dto.getRole());
            }

            return user;
        }).collect(Collectors.toList());

        List<User> savedUsers = userRepository.saveAll(users);
        return ResponseEntity.ok(savedUsers);
    }





    @PostMapping("/register")
    public ResponseEntity<?> registerStudent(@RequestBody StudentWithUserDTO dto) {
        if (dto.getUser() == null || dto.getStudent() == null) {
            return ResponseEntity.badRequest().body("User and Student info are required!");
        }

        // Kiểm tra username trùng
        if (userRepository.findByUsername(dto.getUser().getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        // Tạo User
        User user = new User();
        user.setUsername(dto.getUser().getUsername());
        user.setPassword(passwordEncoder.encode(dto.getUser().getPassword()));
        user.setRole(dto.getUser().getRole());

        // Tạo Student
        Student student = new Student();
        student.setName(dto.getStudent().getName());

        // Gắn quan hệ
        student.setUser(user);
        user.setStudent(student);

        // Lưu vào DB
        userRepository.save(user);

        return ResponseEntity.ok("Registration successful");
    }
}


