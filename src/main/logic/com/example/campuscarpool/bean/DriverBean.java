package com.example.campuscarpool.bean;

import java.time.LocalDate;

public class DriverBean {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private char gender;
    private String email;
    private String phoneNumber;
    private String password;

    public DriverBean(String firstName, String lastName, LocalDate dateOfBirth, char gender, String email, String phoneNumber) {
        this.email = email;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}