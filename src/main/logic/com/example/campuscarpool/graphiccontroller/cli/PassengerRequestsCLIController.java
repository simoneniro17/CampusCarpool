package com.example.campuscarpool.graphiccontroller.cli;

import com.example.campuscarpool.appcontroller.RideRequestController;
import com.example.campuscarpool.bean.PassengerBean;
import com.example.campuscarpool.bean.RideRequestBean;
import com.example.campuscarpool.engineering.Printer;
import com.example.campuscarpool.engineering.Session;
import com.example.campuscarpool.exception.CommandErrorException;
import com.example.campuscarpool.exception.NotFoundException;
import com.example.campuscarpool.viewcli.PassengerRequestsViewCLI;

import java.util.List;

public class PassengerRequestsCLIController implements GraphicCLIController {

    // Costanti per le opzioni del menu
    private static final String PENDING_REQUESTS = "1";
    private static final String ACCEPTED_REQUESTS = "2";
    private static final String REJECTED_REQUESTS = "3";
    private static final String BACK = "4";

    @Override
    public void start() {
        PassengerRequestsViewCLI passengerRequestsViewCLI = new PassengerRequestsViewCLI(this);
        passengerRequestsViewCLI.run();
    }

    public void executeCommand(String inputLine) throws CommandErrorException, NotFoundException {

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

    // Mostra le richieste inviate da un Passenger in base al tipo
    public void displayPassengerRequests(String requestType) throws CommandErrorException, NotFoundException {
        RideRequestController rideRequestController = new RideRequestController();
        PassengerBean passengerBean = Session.getCurrentSession().getPassengerBean();

        List<RideRequestBean> rideRequestBeanList;

        switch (requestType) {
            case PENDING_REQUESTS -> rideRequestBeanList = rideRequestController.retrievePassengerPendingRequests(passengerBean);
            case ACCEPTED_REQUESTS -> rideRequestBeanList = rideRequestController.retrievePassengerAcceptedRequests(passengerBean);
            case REJECTED_REQUESTS -> rideRequestBeanList = rideRequestController.retrievePassengerRejectedRequests(passengerBean);

            default -> throw new CommandErrorException();
        }

        List<RideRequestBean> passengerRequests = rideRequestBeanList;

        // Stampa la lista delle richieste
        printRideRequestDetails(passengerRequests);
    }

    // Per stampare i dettagli della richiesta
    private static void printRideRequestDetails(List<RideRequestBean> rideRequestBeanList) {
        if(!rideRequestBeanList.isEmpty()) {
            StringBuilder table = new StringBuilder();
            table.append("\n------------------------------------------------------------------------------------------------ RIDE REQUESTS -------------------------------------------------------------------------------------------------\n");
            table.append(String.format("%-10s | %-10s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s%n",
                    "RequestID", "RideID", "Departure Date", "Departure Time", "Departure Location",
                    "Destination Location", "Driver First Name", "Driver Last Name", "Driver Email", "Driver Phone"));
            table.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            for (RideRequestBean rideRequestBean : rideRequestBeanList) {
                table.append(String.format("%-10s | %-10s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s%n",
                        rideRequestBean.getIdRideRequest(), rideRequestBean.getIdRide(), rideRequestBean.getDepartureDate(),
                        rideRequestBean.getDepartureTime(), rideRequestBean.getDepartureLocation(),
                        rideRequestBean.getDestinationLocation(), rideRequestBean.getDriverFirstName(),
                        rideRequestBean.getDriverLastName(), rideRequestBean.getDriverEmail(),
                        rideRequestBean.getDriverPhoneNumber()));
            }
            table.append("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

            Printer.printMessage(table.toString());
        }
    }
}