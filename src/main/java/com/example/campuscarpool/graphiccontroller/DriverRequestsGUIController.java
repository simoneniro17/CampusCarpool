package com.example.campuscarpool.graphiccontroller;

import com.example.campuscarpool.Main;
import com.example.campuscarpool.appcontroller.ManageRideRequestController;
import com.example.campuscarpool.bean.DriverBean;
import com.example.campuscarpool.bean.RideRequestBean;
import com.example.campuscarpool.engineering.Session;
import com.example.campuscarpool.engineering.ShowExceptionSupport;
import com.example.campuscarpool.engineering.observer.Observer;
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

public class DriverRequestsGUIController implements Observer {
    @FXML
    private HBox confirmedReqList;
    @FXML
    private HBox pendingReqList;

    public void displayDriverRequests() throws IOException, NotFoundException {
        DriverBean driverBean = Session.getCurrentSession().getDriverBean();

        ManageRideRequestController manageRideRequestController = new ManageRideRequestController();
        List<RideRequestBean> pendingRequestsList = manageRideRequestController.retrieveDriverPendingRequests(driverBean);
        List<RideRequestBean> confirmedRequestsList = manageRideRequestController.retrieveDriverConfirmedRequests(driverBean);

        loadRequests(pendingRequestsList, pendingReqList);
        loadRequests(confirmedRequestsList, confirmedReqList);
    }

    public void loadRequests(List<RideRequestBean> rideRequestBeanList, HBox container) throws IOException {
        for (RideRequestBean rideRequestBean : rideRequestBeanList) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("driverRequestsItem.fxml"));
            Pane requestBox = fxmlLoader.load();
            rideRequestBean.register(this);

            DriverRequestsItemGUIController driverRequestsItemGUIController = fxmlLoader.getController();
            driverRequestsItemGUIController.setPane(requestBox);
            driverRequestsItemGUIController.setRideRequest(rideRequestBean);

            container.getChildren().add(requestBox);
        }
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

    public void toProfile() throws IOException {
        ShowExceptionSupport.showException("Not implemented yet!");
    }

    public void logout() throws IOException {
        LogoutGUIController logoutGUIController = new LogoutGUIController();
        logoutGUIController.logout();
    }

    public void toPreviousScreen() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("driverHomepage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void toHomepage() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("driverHomepage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}