package com.example.CampusCarpool.engineering.factory;

import com.example.CampusCarpool.dao.LoginDAO;
import com.example.CampusCarpool.dao.LoginDAOCSV;
import com.example.CampusCarpool.dao.LoginDAOJDBC;

import java.time.LocalTime;

// Factory per creare istanze di oggetti LoginDAO
public class LoginDAOFactory {

    // Costruttore privato per impedire istanze esterne
    private LoginDAOFactory(){
    }

    private static LoginDAOFactory instance = null;

    // Restituisce istanza factory. Se non esiste, viene creata
    public static LoginDAOFactory getInstance() {
        if(instance == null){
            instance = new LoginDAOFactory();
        }
        return instance;
    }

    // Crea e restituisce un'istanza di LoginDAO
    public LoginDAO createLoginDAO(){
        if (LocalTime.now().getMinute()%2 == 0) {
            return new LoginDAOJDBC();
        } else {
            return new LoginDAOCSV();
        }
    }

}