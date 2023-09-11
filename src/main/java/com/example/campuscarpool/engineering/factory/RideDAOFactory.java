package com.example.campuscarpool.engineering.factory;

import com.example.campuscarpool.dao.RideDAO;
import com.example.campuscarpool.dao.RideDAOJDBC;

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

    // Crea e restituisce un'istanza di RideDAO [return new RideDAOCSV();]
    public RideDAO createRideDAO(){
        return new RideDAOJDBC();
    }
}