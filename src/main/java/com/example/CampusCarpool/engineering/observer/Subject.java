package com.example.CampusCarpool.engineering.observer;

import com.example.CampusCarpool.bean.RideRequestBean;
import com.example.CampusCarpool.model.Ride;
import javafx.scene.layout.Pane;

import java.util.List;
import java.util.Vector;

// Classe astratta per impedirne l'istanziazione
public abstract class Subject {

    private final List<Observer> observersList;

    protected Subject() {
        this((Observer) null);
    }

    protected Subject(Observer observer) {
        this(new Vector<>());

        if (observer != null) {
            this.register(observer);
        }
    }

    protected Subject(List<Observer> observersList) {
        this.observersList = observersList;
    }

    public void register(Observer observer) {
        observersList.add(observer);
    }

    public void unregister(Observer observer) {
        observersList.remove(observer);
    }

    public void notifyObserversDriver(RideRequestBean rideRequestBean, Pane pane) {
        for (Observer observer : observersList) {
            observer.update();
            observer.updateDriverPage(rideRequestBean, pane);
        }
    }

    public void notifyObserversPassenger(RideRequestBean rideRequestBean, Pane pane) {
        for (Observer observer : observersList) {
            observer.update();
            observer.updatePassengerPage(rideRequestBean, pane);
        }
    }
}