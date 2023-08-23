package com.example.CampusCarpool.dao;

import com.example.CampusCarpool.connection.ConnectionDB;
import com.example.CampusCarpool.dao.queries.CRUDQueries;
import com.example.CampusCarpool.dao.queries.RetrieveQueries;
import com.example.CampusCarpool.engineering.Printer;
import com.example.CampusCarpool.exception.DuplicateRideException;
import com.example.CampusCarpool.exception.NotFoundException;
import com.example.CampusCarpool.model.Ride;

import java.io.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public abstract class RideDAO {
    private static final String CSV_FILE_RIDES = "src/main/res/Rides.csv";

    private static final int ID_RIDE = 0;

    public static void addRide(Ride ride) throws DuplicateRideException {
        // JDBC
        Connection connection;

        try {
            connection = ConnectionDB.getConnection();

            ResultSet resultSet = RetrieveQueries.retrieveDistinctRide(connection, ride.getDepartureDate(), Time.valueOf(ride.getDepartureTime()), ride.getDriverEmail());

            if(!resultSet.first()) {
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

            while((line = readFromFile.readLine()) != null){
                data = line.split(",");
                lastId = Integer.parseInt(data[ID_RIDE]);
            }

        } catch (IOException e) {
            Printer.printError(e.getMessage());
        }

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
            outputRide.println(newRide);
            outputRide.close();
        } catch (IOException e) {
            Printer.printError(e.getMessage());
        }

    }

    public abstract List<Ride> retrieveRides(LocalDate departureDate, LocalTime departureTime, String departureLocation, String destinationLocation);
    public abstract Ride findRideById(int idRide);
    public abstract void updateRideAvailableSeats(int idRide);

}
