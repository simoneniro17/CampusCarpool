package com.example.CampusCarpool.graphiccontroller;

import com.example.CampusCarpool.bean.CompatibleRideBean;
import com.example.CampusCarpool.bean.CompatibleRidesListBean;
import com.example.CampusCarpool.bean.RideRequestBean;
import com.example.CampusCarpool.engineering.Session;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class CompatibleRidesItemGUIController {

    private CompatibleRideBean compatibleRide;
    private RideRequestBean rideRequest;
    private Pane pane;

    @FXML
    void sendRequest() throws IOException {
        rideRequest.setIdRide(compatibleRide.getIdRide());
        rideRequest.setPassengerEmail(Session.getCurrentSession().getPassengerBean().getEmail());
        rideRequest.setStatus(0);

        /* SendRequestController sendRequestController = new SendRequestController();

        try {

            sendRequestController.saveRequest(rideRequest);
            ShowExceptionSupport.showException("Your request has been sent!");

        } catch (DuplicateRequestException e) {
            ShowExceptionSupport.showException(e.getMessage());
        }

         */
    }
}
