package com.example.CampusCarpool.graphiccontroller;

import com.example.CampusCarpool.Main;
import com.example.CampusCarpool.appcontroller.ManageRideRequestController;
import com.example.CampusCarpool.appcontroller.RideRequestController;
import com.example.CampusCarpool.bean.DriverBean;
import com.example.CampusCarpool.bean.PassengerBean;
import com.example.CampusCarpool.bean.RideRequestBean;
import com.example.CampusCarpool.engineering.Session;
import com.example.CampusCarpool.engineering.ShowExceptionSupport;
import com.example.CampusCarpool.engineering.observer.Observer;
import com.example.CampusCarpool.exception.NotFoundException;
import com.example.CampusCarpool.model.Passenger;
import com.example.CampusCarpool.model.Ride;
import com.example.CampusCarpool.model.RideRequest;
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

    public void loadRequests(List<RideRequest> rideRequestList, HBox container) throws IOException {
        for (RideRequest rideRequest : rideRequestList) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("passengerRequestsItem.fxml"));
                Pane requestBox = fxmlLoader.load();
                rideRequest.register(this);

                PassengerRequestsItemGUIController passengerRequestsItemGUIController = fxmlLoader.getController();
                passengerRequestsItemGUIController.setPane(requestBox);
                passengerRequestsItemGUIController.setRideRequest(rideRequest);

                container.getChildren().add(requestBox);
            } catch (IOException e) {
                ShowExceptionSupport.showException(e.getMessage());
            }
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