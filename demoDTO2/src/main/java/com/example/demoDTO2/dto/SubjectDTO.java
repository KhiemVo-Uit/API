package com.example.demoDTO2.dto;


import java.util.Set;

public class SubjectDTO {

    private Long id;
    private String name;
    private String code;
    private Set<Long> studentIds;  // Lưu ID của các học sinh học môn này

    // Constructors, getters, setters
    public SubjectDTO() {
    }

    public SubjectDTO(Long id, String name, String code, Set<Long> studentIds) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.studentIds = studentIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Long> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(Set<Long> studentIds) {
        this.studentIds = studentIds;
    }
}
