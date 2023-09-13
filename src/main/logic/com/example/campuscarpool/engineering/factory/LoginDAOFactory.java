package com.example.campuscarpool.engineering.factory;

import com.example.campuscarpool.dao.LoginDAO;
import com.example.campuscarpool.dao.LoginDAOJDBC;

// Factory per creare istanze di oggetti LoginDAO
public class LoginDAOFactory {

    // Costruttore privato per impedire istanze esterne
    private LoginDAOFactory() {
    }

    private static LoginDAOFactory instance = null;

    // Restituisce istanza factory. Se non esiste, viene creata
    public static LoginDAOFactory getInstance() {
        if (instance == null) {
            instance = new LoginDAOFactory();
        }
        return instance;
    }

    // Crea e restituisce un'istanza di LoginDAO [return new LoginDAOCSV(); return new LoginDAOJDBC();]
    public LoginDAO createLoginDAO() {
        return new LoginDAOJDBC();
    }
}