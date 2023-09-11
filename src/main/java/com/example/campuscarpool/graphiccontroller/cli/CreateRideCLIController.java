package com.example.campuscarpool.graphiccontroller.cli;

import com.example.campuscarpool.appcontroller.CreateRideController;
import com.example.campuscarpool.bean.DriverBean;
import com.example.campuscarpool.bean.RideBean;
import com.example.campuscarpool.engineering.Printer;
import com.example.campuscarpool.engineering.Session;
import com.example.campuscarpool.exception.DuplicateRideException;
import com.example.campuscarpool.exception.MessageException;
import com.example.campuscarpool.viewcli.CreateRideFormViewCLI;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateRideCLIController implements GraphicCLIController {

    private CreateRideFormViewCLI createRideFormViewCLI;
    private RideBean rideBean;

    @Override
    public void start() {
        this.createRideFormViewCLI = new CreateRideFormViewCLI(this);
        this.createRideFormViewCLI.run();
    }

    // Per creare il RideBean con i dati del Driver che sta creando la corsa
    public void createRide(LocalDate departureDate, LocalTime departureTime, String departureLocation, String destinationLocation, int availableSeats) {
        DriverBean driverBean = Session.getCurrentSession().getDriverBean();

        this.rideBean = new RideBean(departureDate, departureTime, departureLocation, destinationLocation, availableSeats);
        this.rideBean.setRideBeanDriverInfo(driverBean.getFirstName(), driverBean.getLastName(), driverBean.getEmail(), driverBean.getPhoneNumber());
    }

    // Aggiunta corsa al database
    public void addRide() throws DuplicateRideException, MessageException {
        CreateRideController createRideController = new CreateRideController();
        createRideController.createRide(rideBean);
    }

    // Per far visualizzare un messaggio di conferma per la creazione della corsa
    public void displayCreatedRideMessage() {
        Printer.printMessage("\nYour ride has been successfully created!");
        CreateRideFormViewCLI.printContinue();
    }
}