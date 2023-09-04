package com.example.campuscarpool.graphiccontroller;

import com.example.campuscarpool.appcontroller.ManageRideRequestController;
import com.example.campuscarpool.bean.RideRequestBean;
import com.example.campuscarpool.engineering.observer.Observer;
import com.example.campuscarpool.model.RideRequest;
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
        this.rideRequestBean = new RideRequestBean(rideRequest.getIdRideRequest(), rideRequest.getIdRide(), rideRequest.getPassengerEmail(), rideRequest.getStatus());
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