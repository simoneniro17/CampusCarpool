package com.example.campuscarpool.bean;

import java.time.LocalDate;

public class PassengerBean {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private char gender;
    private String email;
    private String phoneNumber;
    private String password;

    public PassengerBean(String firstName, String lastName, LocalDate dateOfBirth, char gender, String email, String phoneNumber) {
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}