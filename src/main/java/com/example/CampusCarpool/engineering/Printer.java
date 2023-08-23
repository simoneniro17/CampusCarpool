package com.example.CampusCarpool.engineering;

import java.util.logging.Logger;

// Utility per la stampa di messaggi ed errori
public class Printer {

    // Logger globale per la registrazione dei messaggi
    static Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    // Costruttore privato per evitare istanze esterne
    private Printer() {
    }

    // Stampa messaggio di errore utilizzando il logger globale
    public static void printError(String error) {
        log.info(error);
    }

    // Stampa messaggio nella console standard di output
    public static void printMessage(String message) {
        System.out.println(message);
    }

}