package com.example.campuscarpool.graphiccontroller.cli;

import com.example.campuscarpool.appcontroller.ManageRideRequestController;
import com.example.campuscarpool.bean.*;
import com.example.campuscarpool.engineering.Printer;
import com.example.campuscarpool.engineering.Session;
import com.example.campuscarpool.exception.CommandErrorException;
import com.example.campuscarpool.exception.MessageException;
import com.example.campuscarpool.exception.NotFoundException;
import com.example.campuscarpool.viewcli.DriverRequestsViewCLI;

import java.util.List;

public class DriverRequestsCLIController implements GraphicCLIController {

    private DriverRequestsViewCLI driverRequestsViewCLI;

    // Costanti per le opzioni del menu
    private static final String PENDING = "1";
    private static final String CONFIRMED = "2";
    private static final String BACK = "3";
    private static final String ACCEPT = "1";
    private static final String REJECT = "2";

    // Liste per le richieste in sospeso e quelle confermate
    private List<RideRequestBean> pendingRequestsList;
    private List<RideRequestBean> confirmedRequestsList;

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
                // Ci sono richieste in sospeso
                if(!pendingRequestsList.isEmpty())
                    driverRequestsViewCLI.printAction();
                else {
                    this.start();
                }
            }

            case CONFIRMED -> {
                displayDriverRequests(CONFIRMED);
                if(!confirmedRequestsList.isEmpty())
                    driverRequestsViewCLI.printContinue();
                else
                    this.start();
            }

            case BACK -> {
                DriverCLIController driverCLIController = new DriverCLIController();
                driverCLIController.start();
            }

            default -> throw new CommandErrorException();
        }
    }

    // Mostra le richieste inviate a un Driver in base al tipo
    public void displayDriverRequests(String requestType) throws CommandErrorException, NotFoundException {
        ManageRideRequestController manageRideRequestController = new ManageRideRequestController();
        DriverBean driverBean = Session.getCurrentSession().getDriverBean();

        List<RideRequestBean> rideRequestBeanList;

        switch (requestType) {
            case PENDING -> {
                rideRequestBeanList = manageRideRequestController.retrieveDriverPendingRequests(driverBean);
                this.pendingRequestsList = rideRequestBeanList;
            }

            case CONFIRMED -> {
                rideRequestBeanList = manageRideRequestController.retrieveDriverConfirmedRequests(driverBean);
                this.confirmedRequestsList = rideRequestBeanList;
            }

            default -> throw new CommandErrorException();
        }

        List<RideRequestBean> driverRequests = rideRequestBeanList;

        printRideRequestDetails(driverRequests);
    }

    // Per eseguire l'opzione scelta
    public void executeOption(String choice) throws CommandErrorException, MessageException {

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

    // Per aggiornare lo stato della richiesta
    public void updateRideRequestStatus(String newStatus) throws CommandErrorException, MessageException {
        switch (newStatus) {
            case ACCEPT ->  acceptRequest();
            case REJECT ->  rejectRequest();

            default -> throw new CommandErrorException();
        }
    }

    // Per elaborare l'ID della richiesta selezionata dal Driver
    public void executeSelectedId(int idRideRequest) {
        if(idRideRequest == 0) {
            this.start();
        } else {
            manageRequest(idRideRequest);
        }
    }


    // Per gestire la richiesta selezionata
    public void manageRequest(int idRideRequest) {
        for(RideRequestBean ride : pendingRequestsList) {
            if(ride.getIdRideRequest() == idRideRequest) {
                this.rideRequestBean = new RideRequestBean(ride.getIdRideRequest(), ride.getIdRide(),
                        ride.getPassengerEmail(), ride.getStatus());
                driverRequestsViewCLI.printOption();
                return;
            }
        }

        driverRequestsViewCLI.rePrintAction();
    }

    // Per accettare la richiesta
    private void acceptRequest() throws MessageException {
        ManageRideRequestController manageRideRequestController = new ManageRideRequestController();
        manageRideRequestController.confirmRideRequest(rideRequestBean, null);
        rideRequestBean = null;

        Printer.printMessage("\nRequest successfully confirmed.");
        driverRequestsViewCLI.printContinue();
    }

    // Per rifiutare la richiesta
    private void rejectRequest() throws MessageException {
        ManageRideRequestController manageRideRequestController = new ManageRideRequestController();
        manageRideRequestController.rejectRideRequest(rideRequestBean, null);
        rideRequestBean = null;

        Printer.printMessage("\nRequest successfully rejected.");
        driverRequestsViewCLI.printContinue();
    }

    // Per stampare i dettagli di un richiesta
    private static void printRideRequestDetails(List<RideRequestBean> rideRequestBeanList) {
        if(!rideRequestBeanList.isEmpty()) {
            StringBuilder table = new StringBuilder();
            table.append("\n------------------------------------------------------------------------------------------------ RIDE REQUESTS -------------------------------------------------------------------------------------------------\n");
            table.append(String.format("%-10s | %-10s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s%n",
                    "RequestID", "RideID", "Departure Date", "Departure Time", "Departure Location",
                    "Destination Location", "Passenger First Name", "Passenger Last Name", "Passenger Birth", "Passenger Phone"));
            table.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            for (RideRequestBean rideRequestBean : rideRequestBeanList) {
                table.append(String.format("%-10s | %-10s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s%n",
                        rideRequestBean.getIdRideRequest(), rideRequestBean.getIdRide(), rideRequestBean.getDepartureDate(),
                        rideRequestBean.getDepartureTime(), rideRequestBean.getDepartureLocation(),
                        rideRequestBean.getDestinationLocation(), rideRequestBean.getPassengerFirstName(),
                        rideRequestBean.getPassengerLastName(), rideRequestBean.getPassengerBirth(),
                        rideRequestBean.getPassengerPhoneNumber()));
            }
            table.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

            Printer.printMessage(table.toString());
        }
    }
}