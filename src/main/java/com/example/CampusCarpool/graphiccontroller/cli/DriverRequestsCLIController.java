package com.example.CampusCarpool.graphiccontroller.cli;

import com.example.CampusCarpool.appcontroller.ManageRideRequestController;
import com.example.CampusCarpool.appcontroller.RideRequestController;
import com.example.CampusCarpool.bean.*;
import com.example.CampusCarpool.engineering.Printer;
import com.example.CampusCarpool.engineering.Session;
import com.example.CampusCarpool.exception.CommandErrorException;
import com.example.CampusCarpool.exception.NotFoundException;
import com.example.CampusCarpool.model.Ride;
import com.example.CampusCarpool.model.RideRequest;
import com.example.CampusCarpool.viewcli.DriverRequestsViewCLI;

import java.util.ArrayList;
import java.util.List;

public class DriverRequestsCLIController implements GraphicCLIController {

    private DriverRequestsViewCLI driverRequestsViewCLI;

    // Costanti per le scelte dell'utente
    private static final String PENDING = "1";
    private static final String CONFIRMED = "2";
    private static final String BACK = "3";

    private static final String ACCEPT = "1";
    private static final String REJECT = "2";

    // Liste per le richieste in sospeso e quelle confermate
    private List<RideRequest> pendingRequestsList;
    private List<RideRequest> confirmedRequestsList;

    private RideRequestBean rideRequestBean;

    @Override
    public void start() {
        this.driverRequestsViewCLI = new DriverRequestsViewCLI(this);
        driverRequestsViewCLI.run();
    }

    public void executeCommand(String inputLine) throws CommandErrorException, NotFoundException {

        switch(inputLine) {
            case PENDING -> {
                displayDriverRequests(PENDING);
                if(!pendingRequestsList.isEmpty())
                    driverRequestsViewCLI.printAction();
                else {
                    this.start();
                }
            }

            case CONFIRMED -> {
                displayDriverRequests(CONFIRMED);
                this.start();
            }

            case BACK -> {
                DriverCLIController driverCLIController = new DriverCLIController();
                driverCLIController.start();
            }

            default -> throw new CommandErrorException();
        }
    }

    public void displayDriverRequests(String requestType) throws CommandErrorException, NotFoundException {
        ManageRideRequestController manageRideRequestController = new ManageRideRequestController();
        DriverBean driverBean = Session.getCurrentSession().getDriverBean();

        RideRequestsListBean rideRequestsListBean;

        switch (requestType) {
            case PENDING -> {
                rideRequestsListBean = manageRideRequestController.retrieveDriverPendingRequests(driverBean);
                this.pendingRequestsList = rideRequestsListBean.getRideRequestsList();
            }

            case CONFIRMED -> {
                rideRequestsListBean = manageRideRequestController.retrieveDriverConfirmedRequests(driverBean);
                this.confirmedRequestsList = rideRequestsListBean.getRideRequestsList();
            }

            default -> throw new CommandErrorException();
        }

        List<RideRequest> driverRequests = rideRequestsListBean.getRideRequestsList();

        printRideRequestDetails(driverRequests);
    }

    public void executeOption(String choice) throws CommandErrorException {

        switch(choice) {
            case ACCEPT -> {
                updateRideRequestStatus(ACCEPT);
                this.start();
            }

            case REJECT -> {
                updateRideRequestStatus(REJECT);
                this.start();
            }

            case BACK -> this.start();

            default -> throw new CommandErrorException();
        }
    }

    public void updateRideRequestStatus(String newStatus) throws CommandErrorException {

        switch (newStatus) {
            case ACCEPT ->  acceptRequest();
            case REJECT ->  rejectRequest();

            default -> throw new CommandErrorException();
        }
    }

    public void executeSelectedId(int idRideRequest) {
        if(idRideRequest == 0) {
            this.start();
        } else {
            manageRequest(idRideRequest);
        }
    }

    public void manageRequest(int idRideRequest) {
        for(RideRequest rideRequest : pendingRequestsList) {
            if(rideRequest.getIdRideRequest() == idRideRequest) {
                this.rideRequestBean = new RideRequestBean(rideRequest.getIdRideRequest(), rideRequest.getIdRide(), rideRequest.getPassengerEmail(), rideRequest.getStatus());
                driverRequestsViewCLI.printOption();
                return;
            }
        }

        driverRequestsViewCLI.rePrintAction();
    }

    private void acceptRequest() {
        ManageRideRequestController manageRideRequestController = new ManageRideRequestController();
        manageRideRequestController.confirmRideRequest(rideRequestBean);
        rideRequestBean = null;

        Printer.printMessage("\nRequest successfully confirmed.");
        driverRequestsViewCLI.printContinue();
    }

    private void rejectRequest() {
        ManageRideRequestController manageRideRequestController = new ManageRideRequestController();
        manageRideRequestController.rejectRideRequest(rideRequestBean);
        rideRequestBean = null;

        Printer.printMessage("\nRequest successfully rejected.");
        driverRequestsViewCLI.printContinue();
    }

    private static void printRideRequestDetails(List<RideRequest> rideRequestList) {

        if(!rideRequestList.isEmpty()) {
            Printer.printMessage("\n------------------------------------------------------------------------------------------------ RIDE REQUESTS -------------------------------------------------------------------------------------------------");
            System.out.printf("%-10s | %-10s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s%n",
                    "RequestID", "RideID", "Departure Date", "Departure Time", "Departure Location",
                    "Destination Location", "Passenger First Name", "Passenger Last Name", "Passenger Birth", "Passenger Phone");

            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (RideRequest rideRequest : rideRequestList) {
                System.out.printf("%-10s | %-10s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s%n",
                        rideRequest.getIdRideRequest(), rideRequest.getIdRide(), rideRequest.getDepartureDate(),
                        rideRequest.getDepartureTime(), rideRequest.getDepartureLocation(),
                        rideRequest.getDestinationLocation(), rideRequest.getPassengerFirstName(),
                        rideRequest.getPassengerLastName(), rideRequest.getPassengerBirth(),
                        rideRequest.getPassengerPhoneNumber());
            }

            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }
}