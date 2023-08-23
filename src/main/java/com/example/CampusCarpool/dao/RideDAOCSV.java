package com.example.CampusCarpool.dao;

import com.example.CampusCarpool.engineering.Printer;
import com.example.CampusCarpool.exception.MessageException;
import com.example.CampusCarpool.exception.NotFoundException;
import com.example.CampusCarpool.model.Ride;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RideDAOCSV extends RideDAO {

    private static final String CSV_FILE_NAME = "src/main/res/Rides.csv";

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

    @Override
    public Ride findRideById(int idRide) {
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

                    ride = new Ride(departureDate, departureTime, data[DEPARTURE_LOCATION], data[DESTINATION_LOCATION],
                            availableSeats, data[DRIVER_FIRST_NAME], data[DRIVER_LAST_NAME],
                            data[DRIVER_EMAIL], data[DRIVER_PHONE]);
                }
            }

            if (ride == null) {
                throw new NotFoundException("No ride found with id:" + idRide);
            }
        } catch (NotFoundException | IOException e) {
            Printer.printError(e.getMessage());
        }

        return ride;
    }

    @Override
    public List<Ride> retrieveRides(LocalDate departureDate, LocalTime departureTime, String departureLocation, String destinationLocation) {
        List<Ride> rides = new ArrayList<>();
        File file = new File(CSV_FILE_NAME);

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String row;
            String[] data;

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            while ((row = bufferedReader.readLine()) != null) {
                data = row.split(",");

                LocalDate rideDepartureDate = LocalDate.parse(data[DEPARTURE_DATE], dateFormatter);
                LocalTime rideDepartureTime = LocalTime.parse(data[DEPARTURE_TIME], timeFormatter);
                int availableSeats = Integer.parseInt(data[AVAILABLE_SEATS]);

                if (rideDepartureDate.equals(departureDate) && (rideDepartureTime.compareTo(departureTime) >= 0)
                        && (data[DEPARTURE_LOCATION].equalsIgnoreCase(departureLocation)) && (data[DESTINATION_LOCATION].equalsIgnoreCase(destinationLocation))
                        && availableSeats > 0) {

                    int idRide = Integer.parseInt(data[ID_RIDE]);

                    Ride ride = new Ride(idRide, rideDepartureDate, rideDepartureTime, data[DEPARTURE_LOCATION], data[DESTINATION_LOCATION],
                            availableSeats, data[DRIVER_FIRST_NAME], data[DRIVER_LAST_NAME],
                            data[DRIVER_EMAIL], data[DRIVER_PHONE]);

                    rides.add(ride);
                }
            }
        } catch (IOException e) {
            Printer.printError(e.getMessage());
        }

        return rides;
    }

    public void updateRideAvailableSeats(int idRide) {
        File file = new File(CSV_FILE_NAME);
        List<String> updatedLines = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String row;
            String[] data;

            while ((row = bufferedReader.readLine()) != null) {
                data = row.split(",");
                int currentId = Integer.parseInt(data[ID_RIDE]);
                int currentSeats = Integer.parseInt(data[AVAILABLE_SEATS]);

                if (currentId == idRide) {
                    if((currentSeats - 1) < 0) {
                        throw new MessageException("There are no more seats available!");
                    }
                    data[AVAILABLE_SEATS] = String.valueOf(currentSeats - 1);
                }

                String updatedRow = String.join(",", data);
                updatedLines.add(updatedRow);
            }
        } catch (IOException | MessageException e) {
            Printer.printError(e.getMessage());
        }

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (String updatedRow : updatedLines) {
                bufferedWriter.write(updatedRow);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            Printer.printError(e.getMessage());
        }
    }
}
