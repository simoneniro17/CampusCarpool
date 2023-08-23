package com.example.CampusCarpool.graphiccontroller.cli;

import com.example.CampusCarpool.appcontroller.RideRequestController;
import com.example.CampusCarpool.bean.PassengerBean;
import com.example.CampusCarpool.bean.RideRequestsListBean;
import com.example.CampusCarpool.engineering.Printer;
import com.example.CampusCarpool.engineering.Session;
import com.example.CampusCarpool.exception.CommandErrorException;
import com.example.CampusCarpool.model.Ride;
import com.example.CampusCarpool.model.RideRequest;
import com.example.CampusCarpool.viewcli.PassengerRequestsViewCLI;

import java.util.List;

public class PassengerRequestsCLIController implements GraphicCLIController {

    // Costanti per le opzioni del menu
    private final static String PENDING_REQUESTS = "1";
    private final static String ACCEPTED_REQUESTS = "2";
    private final static String REJECTED_REQUESTS = "3";
    private final static String BACK = "4";

    @Override
    public void start() {
        PassengerRequestsViewCLI passengerRequestsViewCLI = new PassengerRequestsViewCLI(this);
        passengerRequestsViewCLI.run();
    }

    public void executeCommand(String inputLine) throws CommandErrorException {

        switch(inputLine) {
            case PENDING_REQUESTS -> {
                displayPassengerRequests(PENDING_REQUESTS);

                // Ritorno al menu del passenger dopo aver completato l'operazione
                this.start();
            }

            case ACCEPTED_REQUESTS -> {
                displayPassengerRequests(ACCEPTED_REQUESTS);

                this.start();
            }

            case REJECTED_REQUESTS -> {
                displayPassengerRequests(REJECTED_REQUESTS);

                this.start();
            }

            case BACK -> {
                PassengerCLIController passengerCLIController = new PassengerCLIController();
                passengerCLIController.start();
            }

            default -> throw new CommandErrorException();
        }
    }

    public void displayPassengerRequests(String requestType) throws CommandErrorException {
        RideRequestController rideRequestController = new RideRequestController();
        PassengerBean passengerBean = Session.getCurrentSession().getPassengerBean();

        RideRequestsListBean rideRequestsListBean;

        switch (requestType) {
            case PENDING_REQUESTS -> rideRequestsListBean = rideRequestController.retrievePassengerPendingRequests(passengerBean);

            case ACCEPTED_REQUESTS -> rideRequestsListBean = rideRequestController.retrievePassengerAcceptedRequests(passengerBean);

            case REJECTED_REQUESTS -> rideRequestsListBean = rideRequestController.retrievePassengerRejectedRequests(passengerBean);

            default -> throw new CommandErrorException();
        }

        List<RideRequest> passengerRequests = rideRequestsListBean.getRideRequestsList();

        printRideRequestDetails(passengerRequests);
    }

    private static void printRideRequestDetails(List<RideRequest> rideRequestList) {

        if(!rideRequestList.isEmpty()) {
            Printer.printMessage("\n------------------------------------------------------------------------------------------------ RIDE REQUESTS -------------------------------------------------------------------------------------------------");
            System.out.printf("%-10s | %-10s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s%n",
                    "RequestID", "RideID", "Departure Date", "Departure Time", "Departure Location",
                    "Destination Location", "Driver First Name", "Driver Last Name", "Driver Email", "Driver Phone");

            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            for (RideRequest rideRequest : rideRequestList) {
                System.out.printf("%-10s | %-10s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s%n",
                        rideRequest.getIdRideRequest(), rideRequest.getIdRide(), rideRequest.getDepartureDate(),
                        rideRequest.getDepartureTime(), rideRequest.getDepartureLocation(),
                        rideRequest.getDestinationLocation(), rideRequest.getDriverFirstName(),
                        rideRequest.getDriverLastName(), rideRequest.getDriverEmail(),
                        rideRequest.getDriverPhoneNumber());
            }

            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            }
    }
}

