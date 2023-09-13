package com.example.campuscarpool.graphiccontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

//  Pop-up per mostrare messaggi o errori
public class MessageBoxGUIController {
    @FXML
    private Label messageTxt;

    //  Per impostare il testo del messaggio
    public void setMessage(String message) {
        this.messageTxt.setText(message);
    }

    //  Per chiudere la finestra quando viene premuto il pulsante
    public void close(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
}
