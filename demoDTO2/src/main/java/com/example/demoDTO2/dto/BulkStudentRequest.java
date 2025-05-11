package com.example.demoDTO2.dto;

import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
public class BulkStudentRequest {
    private List<StudentDTO> students;
    // getter/setter

    public List<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }
}
