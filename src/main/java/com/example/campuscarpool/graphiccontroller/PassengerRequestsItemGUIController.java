package com.example.campuscarpool.graphiccontroller;

import com.example.campuscarpool.bean.RideRequestBean;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class PassengerRequestsItemGUIController {
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

    private RideRequestBean rideRequestBean;
    private Pane pane;

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public void setRideRequest(RideRequestBean rideRequestBeanPar) {
        this.rideRequestBean = rideRequestBeanPar;

        departureDateLabel.setText(rideRequestBean.getDepartureDate().toString());
        departureTimeLabel.setText(rideRequestBean.getDepartureTime().toString());
        departureLocationLabel.setText(rideRequestBean.getDepartureLocation());
        destinationLocationLabel.setText(rideRequestBean.getDestinationLocation());
        firstNameLabel.setText(rideRequestBean.getDriverFirstName());
        lastNameLabel.setText(rideRequestBean.getDriverLastName());
        emailLabel.setText(rideRequestBean.getDriverEmail());
        phoneLabel.setText(rideRequestBean.getDriverPhoneNumber());

        if(rideRequestBean.getStatus() == 2) {
            reqPane.setStyle("-fx-background-color: rgba(255,0,0,0.2);");
        } else if (rideRequestBean.getStatus() == 1) {
            reqPane.setStyle("-fx-background-color: rgba(44,105,95,0.37);");
        }
    }
}