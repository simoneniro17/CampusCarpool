package com.example.CampusCarpool.graphiccontroller.cli;

import com.example.CampusCarpool.appcontroller.BookRideController;
import com.example.CampusCarpool.bean.RideRequestBean;
import com.example.CampusCarpool.engineering.Printer;
import com.example.CampusCarpool.engineering.ScannerSupport;
import com.example.CampusCarpool.engineering.Session;
import com.example.CampusCarpool.engineering.ShowExceptionSupport;
import com.example.CampusCarpool.exception.DuplicateRequestException;
import com.example.CampusCarpool.viewcli.CreateRideFormViewCLI;

public class BookRideCLIController implements GraphicCLIController {

    @Override
    public void start() {
    }

    // Per inviare la richiesta alla corsa specificata tramite ID
    public void sendRideRequest(int idRide) {
        try {
            String passengerEmail = Session.getCurrentSession().getPassengerBean().getEmail();
            RideRequestBean rideRequestBean = new RideRequestBean(idRide, passengerEmail, 0);

            BookRideController bookRideController = new BookRideController();
            bookRideController.sendRequest(rideRequestBean);

            displayRequestSentMessage();
        } catch (DuplicateRequestException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
    }

    public void displayRequestSentMessage() {
        Printer.printMessage("\nYour request has been successfully sent!");
        CreateRideFormViewCLI.printContinue();
    }
}