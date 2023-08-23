package com.example.CampusCarpool.graphiccontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

public class MessageBoxGUIController {
    @FXML
    private Label messageTxt;

    public void setMessage(String message) {
        this.messageTxt.setText(message);
    }

    public void close(ActionEvent event) {
        ((Node)event.getSource()).getScene().getWindow().hide();
    }
}
