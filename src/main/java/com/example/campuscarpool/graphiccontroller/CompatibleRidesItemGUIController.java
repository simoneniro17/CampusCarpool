package com.example.campuscarpool.graphiccontroller;

import com.example.campuscarpool.appcontroller.BookRideController;
import com.example.campuscarpool.bean.CompatibleRideBean;
import com.example.campuscarpool.bean.RideRequestBean;
import com.example.campuscarpool.engineering.Session;
import com.example.campuscarpool.engineering.ShowExceptionSupport;
import com.example.campuscarpool.exception.DuplicateRequestException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class CompatibleRidesItemGUIController {

    @FXML
    private Button bookRideButton;

    @FXML
    private Label departureDateLabel;

    @FXML
    private Label departureTimeLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label phoneLabel;

    private CompatibleRideBean compatibleRide;
    private RideRequestBean rideRequest;
    private Pane pane;

    @FXML
    void sendRequest() throws IOException {
        rideRequest.setIdRide(compatibleRide.getIdRide());
        rideRequest.setPassengerEmail(Session.getCurrentSession().getPassengerBean().getEmail());
        rideRequest.setStatus(0);

        BookRideController bookRideController = new BookRideController();
        try {
            bookRideController.sendRequest(rideRequest);
            ShowExceptionSupport.showException("Your request has been successfully saved!");
        } catch (DuplicateRequestException e) {
            ShowExceptionSupport.showException(e.getMessage());
        }
    }

    public void setData(CompatibleRideBean compatibleRideBean, RideRequestBean rideRequestBean) {
        this.compatibleRide = compatibleRideBean;
        this.rideRequest = rideRequestBean;

        departureDateLabel.setText(String.valueOf(compatibleRideBean.getDepartureDate()));
        departureTimeLabel.setText(String.valueOf(compatibleRideBean.getDepartureTime()));
        firstNameLabel.setText(compatibleRideBean.getDriverFirstName());
        lastNameLabel.setText(compatibleRideBean.getDriverLastName());
        emailLabel.setText(compatibleRideBean.getDriverEmail());
        phoneLabel.setText(compatibleRideBean.getDriverPhoneNumber());
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }
}