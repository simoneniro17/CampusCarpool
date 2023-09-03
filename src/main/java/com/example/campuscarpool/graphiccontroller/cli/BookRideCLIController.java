package com.example.campuscarpool.graphiccontroller.cli;

import com.example.campuscarpool.appcontroller.BookRideController;
import com.example.campuscarpool.bean.RideRequestBean;
import com.example.campuscarpool.engineering.Printer;
import com.example.campuscarpool.engineering.Session;
import com.example.campuscarpool.engineering.ShowExceptionSupport;
import com.example.campuscarpool.exception.DuplicateRequestException;
import com.example.campuscarpool.viewcli.CreateRideFormViewCLI;

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