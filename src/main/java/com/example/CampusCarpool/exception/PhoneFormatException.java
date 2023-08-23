package com.example.CampusCarpool.exception;

public class PhoneFormatException extends Exception{
    public PhoneFormatException(String phone) {
        super("'" + phone +"' phone number not valid, \n please insert a new phone number");
    }
}
