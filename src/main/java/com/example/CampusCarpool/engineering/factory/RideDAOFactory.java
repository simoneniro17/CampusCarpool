package com.example.CampusCarpool.engineering.factory;

import com.example.CampusCarpool.dao.RideDAO;
import com.example.CampusCarpool.dao.RideDAOJDBC;
import com.example.CampusCarpool.dao.RideDAOCSV;

import java.time.LocalTime;

public class RideDAOFactory {
    private RideDAOFactory() {
    }

    private static RideDAOFactory instance = null;

    public static RideDAOFactory getInstance() {
        if(instance == null){
            instance = new RideDAOFactory();
        }
        return instance;
    }

    public RideDAO createRideDAO(){

        if (LocalTime.now().getMinute()%2 == 0) {
            return new RideDAOJDBC();
        } else {
            return new RideDAOCSV();
        }

        //return new RideDAOJDBC();

        //return nre RideDAOCSV();
    }
}
