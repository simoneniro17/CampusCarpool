package com.example.campuscarpool.graphiccontroller;

import com.example.campuscarpool.appcontroller.ManageRideRequestController;
import com.example.campuscarpool.bean.RideRequestBean;
import com.example.campuscarpool.engineering.Printer;
import com.example.campuscarpool.engineering.ShowExceptionSupport;
import com.example.campuscarpool.engineering.observer.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;

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
    private Button rejectReqButton;
    @FXML
    private Button acceptReqButton;
    @FXML
    private HBox hboxButton;
    @FXML
    private ImageView trashIcon;
    @FXML
    private AnchorPane reqPane;

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public void setRideRequest(RideRequestBean rideRequestBeanPar) {
        this.rideRequestBean = rideRequestBeanPar;

        departureDateLabel.setText(rideRequestBean.getDepartureDate().toString());
        departureTimeLabel.setText(rideRequestBean.getDepartureTime().toString());
        departureLocationLabel.setText(rideRequestBean.getDepartureLocation());
        destinationLocationLabel.setText(rideRequestBean.getDestinationLocation());
        firstNameLabel.setText(rideRequestBean.getPassengerFirstName());
        lastNameLabel.setText(rideRequestBean.getPassengerLastName());
        dateOfBirthLabel.setText(rideRequestBean.getPassengerBirth().toString());
        phoneLabel.setText(rideRequestBean.getPassengerPhoneNumber());

        if (rideRequestBean.getStatus() == 1) {
            hboxButton.getChildren().removeAll(acceptReqButton, rejectReqButton);
            trashIcon.setVisible(true);
            reqPane.setStyle("-fx-background-color: rgba(44,105,95,0.37);");
        }
    }

    public void acceptRequest() {
        ManageRideRequestController manageRideRequestController = new ManageRideRequestController();
        this.rideRequestBean.register(this);
        manageRideRequestController.confirmRideRequest(this.rideRequestBean, this.pane);
    }

    public void rejectRequest() throws IOException {
        ManageRideRequestController manageRideRequestController = new ManageRideRequestController();
        this.rideRequestBean.register(this);
        manageRideRequestController.rejectRideRequest(this.rideRequestBean, this.pane);
        ShowExceptionSupport.showException("Request rejected");
    }

    @Override
    public void updateDriverPage(RideRequestBean rideRequestBean, Pane pane) {
        if (this.rideRequestBean.getStatus() == 1) {
            hboxButton.getChildren().removeAll(acceptReqButton, rejectReqButton);
            trashIcon.setVisible(true);
            reqPane.setStyle("-fx-background-color: rgba(44,105,95,0.37);");
        }
    }

    public void toTrash() throws IOException {
        ShowExceptionSupport.showException("Not implemented yet!");
    }
}