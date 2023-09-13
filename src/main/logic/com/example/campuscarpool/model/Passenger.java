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
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public char getGender() {
        return gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}