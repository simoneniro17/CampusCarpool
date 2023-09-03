package com.example.campuscarpool.graphiccontroller;

import com.example.campuscarpool.Main;
import com.example.campuscarpool.appcontroller.ManageRideRequestController;
import com.example.campuscarpool.bean.DriverBean;
import com.example.campuscarpool.bean.RideRequestBean;
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

public class DriverRequestsGUIController implements Observer {
    @FXML
    private HBox confirmedReqList;
    @FXML
    private HBox pendingReqList;

    public void displayDriverRequests() throws IOException, NotFoundException {
        DriverBean driverBean = Session.getCurrentSession().getDriverBean();

        ManageRideRequestController manageRideRequestController = new ManageRideRequestController();
        List<RideRequest> pendingRequestsList = (manageRideRequestController.retrieveDriverPendingRequests(driverBean)).getRideRequestsList();
        List<RideRequest> confirmedRequestsList = (manageRideRequestController.retrieveDriverConfirmedRequests(driverBean)).getRideRequestsList();

        loadRequests(pendingRequestsList, pendingReqList, "driverPendingRequestsItem.fxml");
        loadRequests(confirmedRequestsList, confirmedReqList, "driverConfirmedRequestsItem.fxml");
    }

    public void loadRequests(List<RideRequest> rideRequestList, HBox container, String fxmlResource) throws IOException {
        for (RideRequest rideRequest : rideRequestList) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlResource));
                Pane requestBox = fxmlLoader.load();
                rideRequest.register(this);

                DriverRequestsItemGUIController driverRequestsItemGUIController = fxmlLoader.getController();
                driverRequestsItemGUIController.setPane(requestBox);
                driverRequestsItemGUIController.setRideRequest(rideRequest);

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
        // IGNORE
    }

    @Override
    public void updateDriverPage(RideRequestBean rideRequestBean, Pane pane) {
        if(pendingReqList.getChildren().contains(pane)) {
            pendingReqList.getChildren().remove(pane);
        }

        if(rideRequestBean.getStatus() == 1) {
            confirmedReqList.getChildren().add(pane);
        }
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("driverHomepage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toPreviousScreen() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("driverHomepage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}