package com.example.campuscarpool.graphiccontroller.cli;

import com.example.campuscarpool.appcontroller.BookRideController;
import com.example.campuscarpool.bean.RideRequestBean;
import com.example.campuscarpool.engineering.Printer;
import com.example.campuscarpool.engineering.Session;
import com.example.campuscarpool.engineering.ShowExceptionSupport;
import com.example.campuscarpool.exception.DuplicateRequestException;
import com.example.campuscarpool.viewcli.CreateRideFormViewCLI;

public class BookRideCLIController {

    // Per inviare la richiesta alla corsa specificata tramite ID
    public void sendRideRequest(int idRide) {
        try {
            String passengerEmail = Session.getCurrentSession().getPassengerBean().getEmail();

            //  Viene creato un oggetto RideRequestBean con i dati della richiesta
            RideRequestBean rideRequestBean = new RideRequestBean(idRide, passengerEmail, 0);

            //  Invio della richiesta
            BookRideController bookRideController = new BookRideController();
            bookRideController.sendRequest(rideRequestBean);

            displayRequestSentMessage();
        } catch (DuplicateRequestException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
    }

    //  Per mostrare un messaggio di conferma dopo l'invio della richiesta
    public void displayRequestSentMessage() {
        Printer.printMessage("\nYour request has been successfully sent!");
        CreateRideFormViewCLI.printContinue();
    }
}