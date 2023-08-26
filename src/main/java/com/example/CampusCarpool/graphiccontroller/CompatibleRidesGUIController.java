package com.example.CampusCarpool.graphiccontroller;

import com.example.CampusCarpool.Main;
import com.example.CampusCarpool.bean.CompatibleRideBean;
import com.example.CampusCarpool.bean.CompatibleRidesListBean;
import com.example.CampusCarpool.bean.RideRequestBean;
import com.example.CampusCarpool.engineering.Printer;
import com.example.CampusCarpool.engineering.ShowExceptionSupport;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class CompatibleRidesGUIController {
    @FXML
    private GridPane grid = new GridPane();

    public int displayCompatibleRides(List<CompatibleRideBean> compatibleRides, RideRequestBean rideRequestBean) {
        int column = 0;
        int row = 1;
        int count = 0;

        try {
            for (CompatibleRideBean ride : compatibleRides) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("compatibleRidesItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CompatibleRidesItemGUIController compatibleRidesItemGUIController = fxmlLoader.getController();

                if(count == 0) {
                    count++;
                }

                compatibleRidesItemGUIController.setData(ride, rideRequestBean);

                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            Printer.printError(e.getMessage());
        }
        return count;
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
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("searchRide.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
