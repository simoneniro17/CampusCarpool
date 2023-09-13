package com.example.campuscarpool.engineering;

import com.example.campuscarpool.Main;
import com.example.campuscarpool.graphiccontroller.MessageBoxGUIController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

// Gestione visualizzazione di messaggi di errore ed eccezioni, sia nella CLI che nella GUI
public class ShowExceptionSupport {

    // Costruttore privato per evitare istanze esterne, dato che tutti i metodi sono statici
    private ShowExceptionSupport() {
    }

    // Stampa messaggio di errore nella CLI e attesa di 'Invio' per continuare
    public static void showExceptionCLI(String message) {
        Printer.printError("\n**************************************\n" + message + "\n\tPress ENTER to continue");
        ScannerSupport.waitEnter();
    }

    // Crea una finestra di dialogo GUI per mostrare il messaggio di errore
    public static void showException(String message) throws IOException {

        // Creazione nuovo Stage per il dialog box
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initStyle(StageStyle.UNDECORATED);

        // Caricamento file FXML relativo al dialog box
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Main.class.getResource("messageBox.fxml"));
        Scene scene = null;

        // Creazione scena e associazione al layout FXML caricato
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            Printer.printError(e.getMessage());
        }

        // Recupero controller dall'FXML loader e impostazione messaggio
        MessageBoxGUIController errorBox = fxmlLoader.getController();
        errorBox.setMessage(message);

        // Visualizzazione al centro dello schermo del dialog box
        dialog.setScene(scene);
        dialog.centerOnScreen();
        dialog.show();
    }
}
