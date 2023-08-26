package com.example.CampusCarpool.graphiccontroller;

import com.example.CampusCarpool.appcontroller.ManageRideRequestController;
import com.example.CampusCarpool.bean.RideRequestBean;
import com.example.CampusCarpool.engineering.observer.Observer;
import com.example.CampusCarpool.model.RideRequest;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class PassengerRequestsItemGUIController implements Observer {
    private RideRequestBean rideRequestBean;
    private Pane pane;

    @FXML
    private Label departureDateLabel;

    @FXML
    private Label departureLocationLabel;

    @FXML
    private Label departureTimeLabel;

    @FXML
    private Label destinationLocationLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private AnchorPane reqPane;

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public void setRideRequest(RideRequest rideRequest) {
        setRideRequestBean(rideRequest);

        departureDateLabel.setText(rideRequest.getDepartureDate().toString());
        departureTimeLabel.setText(rideRequest.getDepartureTime().toString());
        departureLocationLabel.setText(rideRequest.getDepartureLocation());
        destinationLocationLabel.setText(rideRequest.getDestinationLocation());
        firstNameLabel.setText(rideRequest.getDriverFirstName());
        lastNameLabel.setText(rideRequest.getDriverLastName());
        emailLabel.setText(rideRequest.getDriverEmail());
        phoneLabel.setText(rideRequest.getDriverPhoneNumber());
    }

    private void setRideRequestBean(RideRequest rideRequest) {
        this.rideRequestBean.setIdRideRequest(rideRequest.getIdRideRequest());
        this.rideRequestBean.setIdRide(rideRequest.getIdRide());
        this.rideRequestBean.setPassengerEmail(rideRequest.getPassengerEmail());
        this.rideRequestBean.setStatus(rideRequest.getStatus());
    }

    @Override
    public void update() {
        // Ignore
    }

    @Override
    public void updatePassengerPage(RideRequestBean rideRequestBean, Pane pane) {
        // Ignore
    }

    @Override
    public void updateDriverPage(RideRequestBean rideRequestBean, Pane pane) {
        // Ignore
    }

}
