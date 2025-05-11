package com.example.demoDTO2.dto;

import java.util.List;

public class BulkUserRequest {
    private List<UserDTO> users;

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}

