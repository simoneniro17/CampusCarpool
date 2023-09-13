package com.example.campuscarpool.graphiccontroller;

import com.example.campuscarpool.appcontroller.ManageRideRequestController;
import com.example.campuscarpool.bean.RideRequestBean;
import com.example.campuscarpool.engineering.ShowExceptionSupport;
import com.example.campuscarpool.engineering.observer.Observer;
import com.example.campuscarpool.exception.MessageException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;

//  Controller grafico per la visualizzazione dei dettagli di una richiesta
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

    //  Per impostare il riquadro della GUI
    public void setPane(Pane pane) {
        this.pane = pane;
    }

    //  Per impostare la richiesta di passaggio da visualizzare e inizializzare i campi con i suoi dati
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

        //  Se la richiesta è stata confermata, vengono nascosti i pulsanti e mostrata l'icona del cestino
        if (rideRequestBean.getStatus() == 1) {
            hboxButton.getChildren().removeAll(acceptReqButton, rejectReqButton);
            trashIcon.setVisible(true);
            reqPane.setStyle("-fx-border-color: #3A887C; -fx-border-width: 3; -fx-border-radius: 20;");
        }
    }

    //  Per gestire l'accettazione di una richiesta
    public void acceptRequest() throws IOException {
        ManageRideRequestController manageRideRequestController = new ManageRideRequestController();
        this.rideRequestBean.register(this);
        try {
            manageRideRequestController.confirmRideRequest(this.rideRequestBean, this.pane);
        } catch (MessageException e) {
            ShowExceptionSupport.showException(e.getMessage());
        }
    }

    //  Per gestire il rifiuto di una richiesta
    public void rejectRequest() throws IOException {
        ManageRideRequestController manageRideRequestController = new ManageRideRequestController();
        this.rideRequestBean.register(this);
        try {
            manageRideRequestController.rejectRideRequest(this.rideRequestBean, this.pane);
        } catch (MessageException e) {
            ShowExceptionSupport.showException(e.getMessage());
        }
        ShowExceptionSupport.showException("Request rejected");
    }

    //  Metodo chiamato quando la richiesta viene aggiornata
    @Override
    public void updateDriverPage(RideRequestBean rideRequestBean, Pane pane) {
        if (this.rideRequestBean.getStatus() == 1) {
            hboxButton.getChildren().removeAll(acceptReqButton, rejectReqButton);
            trashIcon.setVisible(true);
            reqPane.setStyle("-fx-border-color: #3A887C; -fx-border-width: 3; -fx-border-radius: 20;");
        }
    }

    //  Per cancellare una richiesta accettata
    public void toTrash() throws IOException {
        ShowExceptionSupport.showException("Not implemented yet!");
    }
}