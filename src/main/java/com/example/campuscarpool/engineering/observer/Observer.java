package com.example.campuscarpool.engineering.observer;

import com.example.campuscarpool.bean.RideRequestBean;
import javafx.scene.layout.Pane;

// Interfaccia Observer per il pattern Observer
public interface Observer {
    // Per aggiornare la pagina del guidatore con una richiesta di corsa
    void updateDriverPage(RideRequestBean rideRequestBean, Pane pane);
}