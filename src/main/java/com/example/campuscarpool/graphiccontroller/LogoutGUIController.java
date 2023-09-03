package com.example.campuscarpool.graphiccontroller;

import com.example.campuscarpool.Main;
import com.example.campuscarpool.appcontroller.LogoutController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

// Controller per il logout nella GUI
public class LogoutGUIController {

    // Metodo per effettuare il logout
    public void logout() throws IOException {
        // Creazione dell'oggetto LogoutController per gestire il logout
        LogoutController logoutController = new LogoutController();
        logoutController.logout();

        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("loginPage.fxml")));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

}
