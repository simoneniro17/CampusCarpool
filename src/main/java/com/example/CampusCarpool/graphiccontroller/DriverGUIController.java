package com.example.CampusCarpool.graphiccontroller;

import com.example.CampusCarpool.Main;
import com.example.CampusCarpool.engineering.ShowExceptionSupport;
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
}
