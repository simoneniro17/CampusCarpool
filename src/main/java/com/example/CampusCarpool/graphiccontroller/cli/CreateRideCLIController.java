package com.example.CampusCarpool.graphiccontroller.cli;

import com.example.CampusCarpool.appcontroller.CreateRideController;
import com.example.CampusCarpool.bean.DriverBean;
import com.example.CampusCarpool.bean.RideBean;
import com.example.CampusCarpool.engineering.Printer;
import com.example.CampusCarpool.engineering.Session;
import com.example.CampusCarpool.exception.DuplicateRideException;
import com.example.CampusCarpool.exception.MessageException;
import com.example.CampusCarpool.viewcli.CreateRideFormViewCLI;

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

    public void createRide(LocalDate departureDate, LocalTime departureTime, String departureLocation, String destinationLocation, int availableSeats) {
        DriverBean driverBean = Session.getCurrentSession().getDriverBean();

        this.rideBean = new RideBean(departureDate, departureTime, departureLocation, destinationLocation, availableSeats,
                driverBean.getFirstName(), driverBean.getLastName(), driverBean.getEmail(), driverBean.getPhoneNumber());
    }

    public void addRide() throws DuplicateRideException, MessageException {
        CreateRideController createRideController = new CreateRideController();
        createRideController.createRide(rideBean);
    }

    public void displayCreatedRideMessage() {
        Printer.printMessage("\nYour ride has been successfully created!\n");
    }
}
