package com.example.CampusCarpool.graphiccontroller;

import com.example.CampusCarpool.Main;
import com.example.CampusCarpool.bean.CompatibleRidesListBean;
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
    private GridPane rideGrid = new GridPane();


    public int displayCompatibleRides(List<CompatibleRidesListBean> compatibleRides) {
        int column = 0;
        int row = 1;
        int count = 0;

        try {

            for (CompatibleRidesListBean ride : compatibleRides) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("compatibleRidesItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                CompatibleRidesItemGUIController compatibleRidesItemGUIController = fxmlLoader.getController();

                count++;

                if (column == 3) {
                    column = 0;
                    row++;
                }

                rideGrid.add(anchorPane, column++, row);

                rideGrid.setMinWidth(Region.USE_COMPUTED_SIZE);
                rideGrid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                rideGrid.setMaxWidth(Region.USE_PREF_SIZE);

                rideGrid.setMinHeight(Region.USE_COMPUTED_SIZE);
                rideGrid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                rideGrid.setMaxHeight(Region.USE_PREF_SIZE);

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
