package com.example.CampusCarpool.graphiccontroller.cli;

import com.example.CampusCarpool.appcontroller.BookRideController;
import com.example.CampusCarpool.bean.RideRequestBean;
import com.example.CampusCarpool.engineering.Printer;
import com.example.CampusCarpool.engineering.Session;
import com.example.CampusCarpool.engineering.ShowExceptionSupport;
import com.example.CampusCarpool.exception.DuplicateRequestException;
import com.example.CampusCarpool.exception.MessageException;
import com.example.CampusCarpool.exception.NotFoundException;


public class BookRideCLIController implements GraphicCLIController {

    @Override
    public void start() {
    }

    public void sendRideRequest(int idRide) {

        try {
            String passengerEmail = Session.getCurrentSession().getPassengerBean().getEmail();
            RideRequestBean rideRequestBean = new RideRequestBean(idRide, passengerEmail, 0);

            BookRideController bookRideController = new BookRideController();
            bookRideController.sendRequest(rideRequestBean);

            displayRequestSentMessage();
        } catch (MessageException | NotFoundException | DuplicateRequestException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
        }
    }

    public void displayRequestSentMessage() {
        Printer.printMessage("\nYour request has been successfully sent!\n");
    }
}
