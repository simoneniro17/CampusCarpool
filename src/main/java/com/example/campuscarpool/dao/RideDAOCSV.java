package com.example.campuscarpool.dao;

import com.example.campuscarpool.engineering.Printer;
import com.example.campuscarpool.exception.MessageException;
import com.example.campuscarpool.exception.NotFoundException;
import com.example.campuscarpool.model.Ride;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Implementazione del RideDAO usando un file CSV per l'accesso ai dati delle corse
public class RideDAOCSV extends RideDAO {

    // Percorso file CSV contenente i dati delle corse
    private static final String CSV_FILE_NAME = "src/main/res/Rides.csv";

    // Indici delle colonne nel file CSV
    private static final int ID_RIDE = 0;
    private static final int DEPARTURE_DATE = 1;
    private static final int DEPARTURE_TIME = 2;
    private static final int DEPARTURE_LOCATION = 3;
    private static final int DESTINATION_LOCATION = 4;
    private static final int AVAILABLE_SEATS = 5;
    private static final int DRIVER_FIRST_NAME = 6;
    private static final int DRIVER_LAST_NAME = 7;
    private static final int DRIVER_EMAIL = 8;
    private static final int DRIVER_PHONE = 9;

    // Per trovare una corsa dato il suo ID
    @Override
    public Ride findRideById(int idRide) throws NotFoundException {
        Ride ride = null;
        File file = new File(CSV_FILE_NAME);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String row;
            String[] data;

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            while ((row = bufferedReader.readLine()) != null) {
                data = row.split(",");
                if (Integer.parseInt(data[ID_RIDE]) == idRide) {

                    LocalDate departureDate = LocalDate.parse(data[DEPARTURE_DATE], dateFormatter);
                    LocalTime departureTime = LocalTime.parse(data[DEPARTURE_TIME], timeFormatter);
                    int availableSeats = Integer.parseInt(data[AVAILABLE_SEATS]);

                    ride = new Ride(departureDate, departureTime, data[DEPARTURE_LOCATION], data[DESTINATION_LOCATION], availableSeats);
                    ride.setRideDriverInfo(data[DRIVER_FIRST_NAME], data[DRIVER_LAST_NAME], data[DRIVER_EMAIL], data[DRIVER_PHONE]);
                }
            }

            if (ride == null) {
                throw new NotFoundException("No ride found with id:" + idRide);
            }
        } catch (IOException e) {
            Printer.printError(e.getMessage());
        }

        return ride;
    }

    // Per recuperare le corse con determinati parametri di ricerca
    @Override
    public List<Ride> retrieveRides(LocalDate departureDate, LocalTime departureTime, String departureLocation, String destinationLocation) throws NotFoundException{
        List<Ride> rides = new ArrayList<>();
        File file = new File(CSV_FILE_NAME);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String row;
            String[] data;

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            // Lettura di ogni riga del file CSV
            while ((row = bufferedReader.readLine()) != null) {
                data = row.split(",");

                // Conversione valori di data e ora
                LocalDate rideDepartureDate = LocalDate.parse(data[DEPARTURE_DATE], dateFormatter);
                LocalTime rideDepartureTime = LocalTime.parse(data[DEPARTURE_TIME], timeFormatter);
                int availableSeats = Integer.parseInt(data[AVAILABLE_SEATS]);

                // Verifica criteri di ricerca
                if (rideDepartureDate.equals(departureDate) && (rideDepartureTime.compareTo(departureTime) >= 0)
                        && (data[DEPARTURE_LOCATION].equalsIgnoreCase(departureLocation)) && (data[DESTINATION_LOCATION].equalsIgnoreCase(destinationLocation))
                        && availableSeats > 0) {

                    int idRide = Integer.parseInt(data[ID_RIDE]);

                    // Creazione oggetto Ride con i dati corrispondenti
                    Ride ride = new Ride(idRide, rideDepartureDate, rideDepartureTime, data[DEPARTURE_LOCATION], data[DESTINATION_LOCATION], availableSeats);
                    ride.setRideDriverInfo(data[DRIVER_FIRST_NAME], data[DRIVER_LAST_NAME], data[DRIVER_EMAIL], data[DRIVER_PHONE]);

                    // Aggiunta della corsa alla lista
                    rides.add(ride);
                }
            }

            if(rides.isEmpty()) {
                throw new NotFoundException("No rides found this parameters");
            }
        } catch (IOException e) {
            Printer.printError(e.getMessage());
        }

        return rides;
    }

    // Per aggiornare i posti disponibili di una corsa
    public void updateRideAvailableSeats(int idRide) throws MessageException {
        File file = new File(CSV_FILE_NAME);
        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String row;
            String[] data;

            // Lettura di ogni riga del file CSV
            while ((row = bufferedReader.readLine()) != null) {
                data = row.split(",");
                int currentId = Integer.parseInt(data[ID_RIDE]);
                int currentSeats = Integer.parseInt(data[AVAILABLE_SEATS]);

                // Confronto ID fornito con quello iterato
                if (currentId == idRide) {
                    if((currentSeats - 1) < 0) {
                        throw new MessageException("WARNING: you accepted a new request \neven if there are no more\nseats available!");
                    }
                    data[AVAILABLE_SEATS] = String.valueOf(currentSeats - 1);
                }

                // Creazione e aggiunta riga aggiornata alla lista
                String updatedRow = String.join(",", data);
                updatedLines.add(updatedRow);
            }
        } catch (IOException e) {
            Printer.printError(e.getMessage());
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (String updatedRow : updatedLines) {
                // Scrittura riga aggiornata nel file
                bufferedWriter.write(updatedRow);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            Printer.printError(e.getMessage());
        }
    }
}