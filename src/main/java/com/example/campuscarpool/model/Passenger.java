package com.example.campuscarpool.model;

import java.time.LocalDate;

public class Passenger {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private char gender;
    private String email;
    private String phoneNumber;

    public Passenger(String firstName, String lastName, LocalDate dateOfBirth, char gender, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

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
}