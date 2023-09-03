package com.example.campuscarpool.exception;

public class CommandErrorException extends Exception {
    public CommandErrorException() {
        super("Command not found\n");
    }
}
