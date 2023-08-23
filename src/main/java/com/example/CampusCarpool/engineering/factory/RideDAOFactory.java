package com.example.CampusCarpool.engineering.factory;

import com.example.CampusCarpool.dao.RideDAO;
import com.example.CampusCarpool.dao.RideDAOJDBC;
import com.example.CampusCarpool.dao.RideDAOCSV;

import java.time.LocalTime;

// Factory per creare istanze di oggetti RideDAO
public class RideDAOFactory {

    // Costruttore privato per impedire istanze esterne
    private RideDAOFactory() {
    }

    private static RideDAOFactory instance = null;

    // Restituisce istanza factory. Se non esiste, viene creata
    public static RideDAOFactory getInstance() {
        if(instance == null){
            instance = new RideDAOFactory();
        }
        return instance;
    }

    // Crea e restituisce un'istanza di RideDAO
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