package com.example.campuscarpool.dao;

import com.example.campuscarpool.exception.UserNotFoundException;
import com.example.campuscarpool.model.UserProfile;

// Interfaccia per il DAO relativo al login
public interface LoginDAO {

    // Verifica le credenziali dell'utente e restituisce il profilo associato
    UserProfile checkUser(String username, String password) throws UserNotFoundException;
}