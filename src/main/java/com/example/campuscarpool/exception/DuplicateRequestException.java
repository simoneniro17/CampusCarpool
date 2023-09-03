package com.example.campuscarpool.exception;

public class DuplicateRequestException extends Exception {

    public DuplicateRequestException() {
        super("You already sent a request \n for the same ride");
    }
}