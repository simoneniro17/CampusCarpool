package com.example.campuscarpool.graphiccontroller;

import com.example.campuscarpool.Main;
import com.example.campuscarpool.engineering.ShowExceptionSupport;
import com.example.campuscarpool.exception.NotFoundException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PassengerGUIController {

    //  Per andare alla schermata di ricerca di una corsa
    public void toSearch() throws IOException {
        Stage stage = Main.getStage();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("searchRide.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    //  Per andare alla schermata relativa alle richieste inviate
    public void toRequests() throws IOException {
        Stage stage = Main.getStage();

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("passengerRequests.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        PassengerRequestsGUIController passengerRequestsGUIController = fxmlLoader.getController();
        try {
            passengerRequestsGUIController.displayRequests();
        } catch (NotFoundException e) {
            ShowExceptionSupport.showException(e.getMessage());
        }

        stage.setScene(scene);
        stage.show();
    }

    //  Per effettuare il logout
    public void logout() throws IOException {
        LogoutGUIController logoutGUIController = new LogoutGUIController();
        logoutGUIController.logout();
    }

    //  Per visualizzare i dettagli del profilo
    public void toProfile() throws IOException {
        ShowExceptionSupport.showException("Not implemented yet!");
    }
}