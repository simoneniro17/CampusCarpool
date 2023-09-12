package com.example.campuscarpool.graphiccontroller;

import com.example.campuscarpool.Main;
import com.example.campuscarpool.appcontroller.RideRequestController;
import com.example.campuscarpool.bean.PassengerBean;
import com.example.campuscarpool.bean.RideRequestBean;
import com.example.campuscarpool.engineering.Printer;
import com.example.campuscarpool.engineering.Session;
import com.example.campuscarpool.engineering.ShowExceptionSupport;
import com.example.campuscarpool.engineering.observer.Observer;
import com.example.campuscarpool.exception.NotFoundException;
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

public class PassengerRequestsGUIController {
    @FXML
    private HBox confirmedReqList;
    @FXML
    private HBox pendingReqList;
    @FXML
    private HBox rejectedReqList;

    public void displayRequests() throws IOException, NotFoundException {
        PassengerBean passengerBean = Session.getCurrentSession().getPassengerBean();

        RideRequestController rideRequestController = new RideRequestController();
        List<RideRequestBean> pendingRequestsList = (rideRequestController.retrievePassengerPendingRequests(passengerBean));
        List<RideRequestBean> acceptedRequestsList = (rideRequestController.retrievePassengerAcceptedRequests(passengerBean));
        List<RideRequestBean> rejectedRequestsList = (rideRequestController.retrievePassengerRejectedRequests(passengerBean));

        loadRequests(pendingRequestsList, pendingReqList);
        loadRequests(acceptedRequestsList, confirmedReqList);
        loadRequests(rejectedRequestsList, rejectedReqList);
    }

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

    public void logout() throws IOException {
        LogoutGUIController logoutGUIController = new LogoutGUIController();
        logoutGUIController.logout();
    }

    public void toHomepage() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("passengerHomepage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toProfile() throws IOException {
        ShowExceptionSupport.showException("Not implemented yet!");
    }

    public void toPreviousScreen() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("passengerHomepage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}