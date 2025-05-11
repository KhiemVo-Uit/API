package com.example.demoDTO2.dto;


public class StudentWithUserDTO {
    private UserDTO user;
    private StudentDTO student;

    public StudentWithUserDTO() {
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }
}

