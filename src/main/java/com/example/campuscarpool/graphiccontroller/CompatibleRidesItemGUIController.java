package com.example.campuscarpool.graphiccontroller;

import com.example.campuscarpool.appcontroller.BookRideController;
import com.example.campuscarpool.bean.CompatibleRideBean;
import com.example.campuscarpool.bean.RideRequestBean;
import com.example.campuscarpool.bean.SearchRideBean;
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
        String passengerEmail = Session.getCurrentSession().getPassengerBean().getEmail();
        int idRide = compatibleRide.getIdRide();
        rideRequest = new RideRequestBean(idRide, passengerEmail, 0);

        BookRideController bookRideController = new BookRideController();
        try {
            bookRideController.sendRequest(rideRequest);
            ShowExceptionSupport.showException("Your request has been successfully saved!");
        } catch (DuplicateRequestException e) {
            ShowExceptionSupport.showException(e.getMessage());
        }
    }

    public void setData(CompatibleRideBean compatibleRideBean) {
        this.compatibleRide = compatibleRideBean;

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