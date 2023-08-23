package com.example.CampusCarpool.bean;

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
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    /* public DriverBean(String firstName, String lastName, LocalDate dateOfBirth, char gender, String email, String phoneNumber, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.setPhoneNumber(phoneNumber);
        this.password = password;
    } */

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public char getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }
}
