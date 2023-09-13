package com.example.campuscarpool.graphiccontroller;

import com.example.campuscarpool.Main;
import com.example.campuscarpool.appcontroller.BookRideController;
import com.example.campuscarpool.bean.CompatibleRideBean;
import com.example.campuscarpool.bean.RideRequestBean;
import com.example.campuscarpool.bean.SearchRideBean;
import com.example.campuscarpool.engineering.Session;
import com.example.campuscarpool.engineering.ShowExceptionSupport;
import com.example.campuscarpool.exception.DuplicateRequestException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

//  Controller grafico per la visualizzazione dei dettagli di una corsa compatibile
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

    //  Per inviare una richiesta
    @FXML
    void sendRequest() throws IOException {
        String passengerEmail = Session.getCurrentSession().getPassengerBean().getEmail();
        int idRide = compatibleRide.getIdRide();
        rideRequest = new RideRequestBean(idRide, passengerEmail, 0);

        BookRideController bookRideController = new BookRideController();
        try {
            bookRideController.sendRequest(rideRequest);
            ShowExceptionSupport.showException("Your request has been successfully sent!\nYou have been redirected to homepage.");
            toHomepage();
        } catch (DuplicateRequestException e) {
            ShowExceptionSupport.showException(e.getMessage());
        }
    }

    //  Per impostare i dati della corsa compatibile nella GUI
    public void setData(CompatibleRideBean compatibleRideBean) {
        this.compatibleRide = compatibleRideBean;

        departureDateLabel.setText(String.valueOf(compatibleRideBean.getDepartureDate()));
        departureTimeLabel.setText(String.valueOf(compatibleRideBean.getDepartureTime()));
        firstNameLabel.setText(compatibleRideBean.getDriverFirstName());
        lastNameLabel.setText(compatibleRideBean.getDriverLastName());
        emailLabel.setText(compatibleRideBean.getDriverEmail());
        phoneLabel.setText(compatibleRideBean.getDriverPhoneNumber());
    }

    //  Per impostare il riquadro della GUI
    public void setPane(Pane pane) {
        this.pane = pane;
    }

    //  Per tornare alla homepage
    public void toHomepage() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("passengerHomepage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}