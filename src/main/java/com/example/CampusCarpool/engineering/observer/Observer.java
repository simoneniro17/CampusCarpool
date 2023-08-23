package com.example.CampusCarpool.engineering.observer;

import com.example.CampusCarpool.bean.RideRequestBean;
import com.example.CampusCarpool.model.Ride;
import javafx.scene.layout.Pane;

public interface Observer {
    void update();

    public void updateDriverPage(RideRequestBean rideRequestBean, Pane pane);
    public void updatePassengerPage(RideRequestBean rideRequestBean, Pane pane);
}
