package com.example.campuscarpool.graphiccontroller;

import com.example.campuscarpool.Main;
import com.example.campuscarpool.engineering.ShowExceptionSupport;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DriverGUIController {

    public void logout() throws IOException {
        LogoutGUIController logoutGUIController = new LogoutGUIController();
        logoutGUIController.logout();
    }

    public void toProfile() throws IOException {
        ShowExceptionSupport.showException("Not implemented yet!");
    }

    public void toCreateRide() throws IOException {
        Stage stage = Main.getStage();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("createRide.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void toManageRequests() throws IOException {
        Stage stage = Main.getStage();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("driverRequests.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
