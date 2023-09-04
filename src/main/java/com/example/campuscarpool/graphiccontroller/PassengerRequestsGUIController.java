package com.example.campuscarpool.graphiccontroller;

import com.example.campuscarpool.Main;
import com.example.campuscarpool.appcontroller.RideRequestController;
import com.example.campuscarpool.bean.PassengerBean;
import com.example.campuscarpool.bean.RideRequestBean;
import com.example.campuscarpool.engineering.Printer;
import com.example.campuscarpool.engineering.Session;
import com.example.campuscarpool.engineering.ShowExceptionSupport;
import com.example.campuscarpool.engineering.observer.Observer;
import com.example.campuscarpool.model.RideRequest;
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

public class PassengerRequestsGUIController implements Observer {
    @FXML
    private HBox confirmedReqList;
    @FXML
    private HBox pendingReqList;
    @FXML
    private HBox rejectedReqList;

    public void displayRequests() throws IOException {
        PassengerBean passengerBean = Session.getCurrentSession().getPassengerBean();

        RideRequestController rideRequestController = new RideRequestController();
        List<RideRequest> pendingRequestsList = (rideRequestController.retrievePassengerPendingRequests(passengerBean).getRideRequestsList());
        List<RideRequest> acceptedRequestsList = (rideRequestController.retrievePassengerAcceptedRequests(passengerBean).getRideRequestsList());
        List<RideRequest> rejectedRequestsList = (rideRequestController.retrievePassengerRejectedRequests(passengerBean).getRideRequestsList());

        loadRequests(pendingRequestsList, pendingReqList);
        loadRequests(acceptedRequestsList, confirmedReqList);
        loadRequests(rejectedRequestsList, rejectedReqList);
    }

    private void loadRequests(List<RideRequest> rideRequestList, HBox container) throws IOException {
        for (RideRequest rideRequest : rideRequestList) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("passengerRequestsItem.fxml"));
            Pane requestBox = fxmlLoader.load();

            //TODO
            if(container == pendingReqList || container == rejectedReqList )
                rideRequest.register(this);

            PassengerRequestsItemGUIController passengerRequestsItemGUIController = fxmlLoader.getController();
            passengerRequestsItemGUIController.setRideRequest(rideRequest);

            container.getChildren().add(requestBox);
        }
    }

    @Override
    public void update() {
        // Ignore
    }

    @Override
    public void updatePassengerPage(RideRequestBean rideRequestBean, Pane pane) {
        if (pendingReqList.getChildren().contains(pane))
            pendingReqList.getChildren().remove(pane);

        if(rideRequestBean.getStatus() == 1) {
            confirmedReqList.getChildren().add(pane);
        } else if (rideRequestBean.getStatus() == 2) {
            rejectedReqList.getChildren().add(pane);
        }
    }

    @Override
    public void updateDriverPage(RideRequestBean rideRequestBean, Pane pane) {
        // IGNORE
    }

    public void logout() throws IOException {
        LogoutGUIController logoutGUIController = new LogoutGUIController();
        logoutGUIController.logout();
    }

    public void toProfile() throws IOException {
        ShowExceptionSupport.showException("Not implemented yet!");
    }

    public void toHomepage() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("passengerHomepage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toPreviousScreen() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("passengerHomepage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}