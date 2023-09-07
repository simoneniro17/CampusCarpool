package com.example.campuscarpool.dao;

import com.example.campuscarpool.connection.ConnectionDB;
import com.example.campuscarpool.dao.queries.CRUDQueries;
import com.example.campuscarpool.dao.queries.RetrieveQueries;
import com.example.campuscarpool.engineering.Printer;
import com.example.campuscarpool.exception.DuplicateRideException;
import com.example.campuscarpool.exception.MessageException;
import com.example.campuscarpool.exception.NotFoundException;
import com.example.campuscarpool.model.Ride;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

// Gestisce l'accesso ai dati delle corse
public abstract class RideDAO {

    // Percorso del file CSV contenente i dati delle corse
    private static final String CSV_FILE_RIDES = "src/main/res/Rides.csv";

    // Indice colonna idRide nel file CSV
    private static final int ID_RIDE = 0;

    // Per aggiungere una nuova corsa
    public static void addRide(Ride ride) throws DuplicateRideException {
        // JDBC
        Connection connection;

        try {

            // Ottenimento connessione al database
            connection = ConnectionDB.getConnection();

            // Query per verificare che non ci sia già una corsa simile
            ResultSet resultSet = RetrieveQueries.retrieveDistinctRide(connection, ride.getDepartureDate(), Time.valueOf(ride.getDepartureTime()), ride.getDriverEmail());

            // Nessuna corsa trovata
            if(!resultSet.first()) {
                // Query per inserire una nuova corsa
                CRUDQueries.insertNewRide(connection, ride);
            } else {
                throw new DuplicateRideException();
            }

        } catch (SQLException e) {
            Printer.printError(e.getMessage());
        }

        // CSV
        int lastId = 0;
        String line;
        String[] data;
        File file = new File(CSV_FILE_RIDES);

        try (BufferedReader readFromFile = new BufferedReader(new FileReader(file))){
            // Per calcolare l'ultimo ID presente nel file
            while((line = readFromFile.readLine()) != null){
                data = line.split(",");
                lastId = Integer.parseInt(data[ID_RIDE]);
            }
        } catch (IOException e) {
            Printer.printError(e.getMessage());
        }

        // La nuova corsa da aggiungere al file
        String newRide = String.valueOf(lastId + 1);
        newRide = newRide.concat(",");
        newRide = newRide.concat(String.valueOf(ride.getDepartureDate()));
        newRide = newRide.concat(",");
        newRide = newRide.concat(String.valueOf(ride.getDepartureTime()));
        newRide = newRide.concat(",");
        newRide = newRide.concat(String.valueOf(ride.getDepartureLocation()));
        newRide = newRide.concat(",");
        newRide = newRide.concat(String.valueOf(ride.getDestinationLocation()));
        newRide = newRide.concat(",");
        newRide = newRide.concat(String.valueOf(ride.getAvailableSeats()));
        newRide = newRide.concat(",");
        newRide = newRide.concat(String.valueOf(ride.getDriverFirstName()));
        newRide = newRide.concat(",");
        newRide = newRide.concat(String.valueOf(ride.getDriverLastName()));
        newRide = newRide.concat(",");
        newRide = newRide.concat(String.valueOf(ride.getDriverEmail()));
        newRide = newRide.concat(",");
        newRide = newRide.concat(String.valueOf(ride.getDriverPhoneNumber()));

        File fileRides = new File(CSV_FILE_RIDES);

        try (PrintWriter outputRide = new PrintWriter(new BufferedWriter(new FileWriter(fileRides, true)))){
            // Scrittura nuova corsa
            outputRide.println(newRide);
        } catch (IOException e) {
            Printer.printError(e.getMessage());
        }
    }

    // Metodi astratti per il recupero, la ricerca e l'aggiornamento delle corse
    public abstract List<Ride> retrieveRides(LocalDate departureDate, LocalTime departureTime, String departureLocation, String destinationLocation) throws NotFoundException;
    public abstract Ride findRideById(int idRide) throws NotFoundException;
    public abstract void updateRideAvailableSeats(int idRide) throws MessageException;
}