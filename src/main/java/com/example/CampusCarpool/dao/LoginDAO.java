package com.example.CampusCarpool.dao;

import com.example.CampusCarpool.exception.UserNotFoundException;
import com.example.CampusCarpool.model.UserProfile;

// Interfaccia per il DAO relativo al login
public interface LoginDAO {

    // Verifica le credenziali dell'utente e restituisce il profilo associato
    UserProfile checkUser(String username, String password) throws UserNotFoundException;
}