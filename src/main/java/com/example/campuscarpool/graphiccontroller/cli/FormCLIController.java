package com.example.campuscarpool.graphiccontroller.cli;

import com.example.campuscarpool.exception.MessageException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FormCLIController {

    // Per validare e restituire la data di partenza
    public LocalDate validateDepartureDate(String inputLine) throws MessageException {
        LocalDate departureDate;

        try {
            departureDate = LocalDate.parse(inputLine);
        } catch (DateTimeParseException e) {
            throw new MessageException("Invalid date format. Please, be sure you inserted the correct values in each field.");
        }

        return departureDate;
    }

    // Per validare e restituire l'ora di partenza
    public LocalTime validateDepartureTime(String inputLine) throws MessageException {
        LocalTime departureTime;

        try {
            departureTime = LocalTime.parse(inputLine, DateTimeFormatter.ofPattern("HH:mm:ss"));
        } catch (DateTimeParseException e) {
            throw new MessageException("Invalid time format. Please, be sure you inserted the correct values in each field.");
        }

        return departureTime;
    }

    // Per formattare correttamente il luogo inserito
    public String executeLocation(String inputLine) throws MessageException {
        StringBuilder formattedLocation = new StringBuilder();

        try {
            // Rimozione spazi all'inizio e alla fine della stringa
            String trimmedInput = inputLine.trim();

            if (trimmedInput.isEmpty()) {
                throw new MessageException("Location cannot be empty. Please, enter a valid location.");
            }

            // Divisione stringa in parole utilizzando gli spazi come delimitatori
            String[] words = trimmedInput.split("\\s+");

            // Costruzione nuova stringa in cui ogni parola inizia con una maiuscola
            for (String word : words) {
                if (!word.isEmpty()) {  // Ignoriamo eventuali spazi multipli
                    String formattedWord = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
                    formattedLocation.append(formattedWord).append(" ");
                }}
        } catch (Exception e) {
            throw new MessageException("An error occurred while formatting your location. Please, re-insert your values.");
        }

        // Rimozione spazio finale e restituzione stringa risultante
        return formattedLocation.toString().trim();
    }

    // Per controllare e restituire il numero di posti disponibili
    public int executeAvailableSeats(String inputLine) throws MessageException {
        int seats;

        try {
            seats = Integer.parseInt(inputLine);
            if (seats < 1 || seats > 4) {
                throw new MessageException("Number of seats must be between 1 and 4!");
            }
        } catch (NumberFormatException e) {
            throw new MessageException("Invalid input for number of seats.");
        }
        return seats;
    }
}