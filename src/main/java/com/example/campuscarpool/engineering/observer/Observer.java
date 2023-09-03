package com.example.campuscarpool.engineering.observer;

import com.example.campuscarpool.bean.RideRequestBean;
import javafx.scene.layout.Pane;

// Interfaccia Observer per il pattern Observer
public interface Observer {

    // Per aggiornare l'Observer
    void update();

    // Per aggiornare la pagina del guidatore con una richiesta di corsa
    public void updateDriverPage(RideRequestBean rideRequestBean, Pane pane);

    // Per aggiornare la pagina del passeggero sullo stato di una sua richiesta
    public void updatePassengerPage(RideRequestBean rideRequestBean, Pane pane);
}
