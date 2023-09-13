package com.example.campuscarpool.graphiccontroller;

import com.example.campuscarpool.Main;
import com.example.campuscarpool.appcontroller.RideRequestController;
import com.example.campuscarpool.bean.PassengerBean;
import com.example.campuscarpool.bean.RideRequestBean;
import com.example.campuscarpool.engineering.Session;
import com.example.campuscarpool.engineering.ShowExceptionSupport;
import com.example.campuscarpool.exception.NotFoundException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

//  Controller grafico per la visualizzazione delle richieste inviate da un Passenger
public class PassengerRequestsGUIController {
    @FXML
    private HBox confirmedReqList;
    @FXML
    private HBox pendingReqList;
    @FXML
    private HBox rejectedReqList;

    //  Per visualizzare le richieste inviate da un passeggero
    public void displayRequests() throws IOException, NotFoundException {
        PassengerBean passengerBean = Session.getCurrentSession().getPassengerBean();

        //  Recupero delle richieste in base al loro stato
        RideRequestController rideRequestController = new RideRequestController();
        List<RideRequestBean> pendingRequestsList = (rideRequestController.retrievePassengerPendingRequests(passengerBean));
        List<RideRequestBean> acceptedRequestsList = (rideRequestController.retrievePassengerAcceptedRequests(passengerBean));
        List<RideRequestBean> rejectedRequestsList = (rideRequestController.retrievePassengerRejectedRequests(passengerBean));

        //  Caricamento delle richieste nei contenitori appropriati
        loadRequests(pendingRequestsList, pendingReqList);
        loadRequests(acceptedRequestsList, confirmedReqList);
        loadRequests(rejectedRequestsList, rejectedReqList);
    }

    //  Per caricare le richieste in un contenitore
    private void loadRequests(List<RideRequestBean> rideRequestBeanList, HBox container) throws IOException {
        for (RideRequestBean rideRequestBean : rideRequestBeanList) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("passengerRequestsItem.fxml"));
            Pane requestBox = fxmlLoader.load();

            PassengerRequestsItemGUIController passengerRequestsItemGUIController = fxmlLoader.getController();
            passengerRequestsItemGUIController.setRideRequest(rideRequestBean);

            container.getChildren().add(requestBox);
        }
    }

    //  Per effettuare il logout
    public void logout() throws IOException {
        LogoutGUIController logoutGUIController = new LogoutGUIController();
        logoutGUIController.logout();
    }

    //  Per andare alla homepage
    public void toHomepage() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("passengerHomepage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //  Per visualizzare i dettagli del profilo
    public void toProfile() throws IOException {
        ShowExceptionSupport.showException("Not implemented yet!");
    }

    //  Per tornare alla schermata precedente
    public void toPreviousScreen() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("passengerHomepage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}