package com.example.CampusCarpool.engineering.observer;

import com.example.CampusCarpool.bean.RideRequestBean;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.Vector;

// Classe astratta per impedirne l'istanziazione diretta
public abstract class Subject {

    private final List<Observer> observersList;

    // Costruttore protetto senza argomenti
    protected Subject() {
        this((Observer) null);
    }

    // Costruttore protetto con observer iniziale
    protected Subject(Observer observer) {
        this(new Vector<>());

        if (observer != null) {
            this.register(observer);
        }
    }

    // Costruttore protetto con lista di observer
    protected Subject(List<Observer> observersList) {
        this.observersList = observersList;
    }

    // Registra un observer
    public void register(Observer observer) {
        observersList.add(observer);
    }

    // Rimuove un observer
    public void unregister(Observer observer) {
        observersList.remove(observer);
    }

    // Modifica tutti gli observer e aggiorna la pagina del guidatore
    public void notifyObserversDriver(RideRequestBean rideRequestBean, Pane pane) {
        for (Observer observer : observersList) {
            observer.update();
            observer.updateDriverPage(rideRequestBean, pane);
        }
    }

    // Modifica tutti gli observer e aggiorna la pagina del passeggero
    public void notifyObserversPassenger(RideRequestBean rideRequestBean, Pane pane) {
        for (Observer observer : observersList) {
            observer.update();
            observer.updatePassengerPage(rideRequestBean, pane);
        }
    }
}