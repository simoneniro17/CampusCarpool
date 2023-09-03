package com.example.campuscarpool.dao;

import com.example.campuscarpool.engineering.Printer;
import com.example.campuscarpool.exception.NotFoundException;
import com.example.campuscarpool.exception.UserNotFoundException;
import com.example.campuscarpool.model.UserProfile;

import java.io.*;

// Implementazione del LoginDAO usando un file CSV per l'accesso ai dati degli utenti
public class LoginDAOCSV implements LoginDAO {

    // Percorso file CSV contenente i dati degli utenti
    private static final String CSV_FILE_NAME = "src/main/res/Users.csv";

    // Indici delle colonne nel file CSV
    private static final int USERNAME = 0;
    private static final int PASSWORD = 1;
    private static final int ROLE = 2;


    // Verifica se credenziali di accesso fornite corrispondono a un utente registrato
    @Override
    public UserProfile checkUser(String username, String password) throws UserNotFoundException {
        int role;
        UserProfile userProfile = null;
        File file = new File(CSV_FILE_NAME);

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            String row;
            String[] data;

            // Leggiamo ogni riga del file CSV
            while((row = bufferedReader.readLine()) != null){
                data = row.split(",");
                if(data[USERNAME].equals(username) && data[PASSWORD].equals(password)){
                    switch(data[ROLE]){
                        case "Driver" -> role = 1;
                        case "Passenger" -> role = 2;
                        default -> throw new NotFoundException("No role found");
                    }
                    userProfile = new UserProfile(role, username);
                }
            }

            // L'utente non è stato trovato
            if(userProfile == null){
                throw new UserNotFoundException();
            }

        } catch (IOException | NotFoundException e) {
            Printer.printError(e.getMessage());
        }

        return userProfile;
    }
}