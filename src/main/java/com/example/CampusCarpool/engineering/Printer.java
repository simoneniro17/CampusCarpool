package com.example.CampusCarpool.engineering;

import java.util.logging.Logger;

public class Printer {
    static Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private Printer() {
    }

    public static void printError(String error) {
        log.info(error);
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
