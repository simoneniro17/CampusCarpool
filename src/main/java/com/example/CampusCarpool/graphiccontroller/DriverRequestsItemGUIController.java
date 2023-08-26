package com.example.CampusCarpool.graphiccontroller;

import com.example.CampusCarpool.appcontroller.ManageRideRequestController;
import com.example.CampusCarpool.bean.RideRequestBean;
import com.example.CampusCarpool.engineering.observer.Observer;
import com.example.CampusCarpool.model.Ride;
import com.example.CampusCarpool.model.RideRequest;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class DriverRequestsItemGUIController implements Observer {
    private RideRequestBean rideRequestBean;
    private Pane pane;

    @FXML
    private Label departureDateLabel;
    @FXML
    private Label departureTimeLabel;
    @FXML
    private Label departureLocationLabel;
    @FXML
    private Label destinationLocationLabel;
    @FXML
    private Label dateOfBirthLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Button rejectRequestButton;
    @FXML
    private Button acceptRequestButton;
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
        firstNameLabel.setText(rideRequest.getPassengerFirstName());
        lastNameLabel.setText(rideRequest.getPassengerLastName());
        dateOfBirthLabel.setText(rideRequest.getPassengerBirth().toString());
        phoneLabel.setText(rideRequest.getPassengerPhoneNumber());

        if(rideRequest.getStatus() == 1) {
            reqPane.getChildren().removeAll(rejectRequestButton, acceptRequestButton);
        }
    }

    private void setRideRequestBean(RideRequest rideRequest) {
        this.rideRequestBean.setIdRideRequest(rideRequest.getIdRideRequest());
        this.rideRequestBean.setIdRide(rideRequest.getIdRide());
        this.rideRequestBean.setPassengerEmail(rideRequest.getPassengerEmail());
        this.rideRequestBean.setStatus(rideRequest.getStatus());
    }

    public void acceptRequest() {
        ManageRideRequestController manageRideRequestController = new ManageRideRequestController();
        this.rideRequestBean.register(this);
        manageRideRequestController.confirmRideRequest(this.rideRequestBean, this.pane);

    }

    public void rejectRequest() {
        ManageRideRequestController manageRideRequestController = new ManageRideRequestController();
        this.rideRequestBean.register(this);
        manageRideRequestController.rejectRideRequest(this.rideRequestBean, this.pane);

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
