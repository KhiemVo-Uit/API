package com.example.demoDTO2.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class StudentDtoExel {
    private String name;
    private UserDTO user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
