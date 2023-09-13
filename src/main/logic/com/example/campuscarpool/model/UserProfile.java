package com.example.campuscarpool.model;

public class UserProfile {
    private int role;
    private String username;

    public UserProfile(int role, String username) {
        this.role = role;
        this.username = username;
    }

    public int getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }
}