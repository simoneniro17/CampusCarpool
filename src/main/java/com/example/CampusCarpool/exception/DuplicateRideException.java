package com.example.CampusCarpool.exception;

public class DuplicateRideException extends Exception {

    public DuplicateRideException() {
        super("You already create a ride \n of this type");
    }
}
