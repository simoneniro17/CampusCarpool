package com.example.campuscarpool.engineering;

import java.util.Scanner;

// Classe che fornisce metodi per attendere che l'utente premi il tasto "invio"
public class ScannerSupport {

    // Costruttore privato per evitare istanze esterne
    private ScannerSupport() {
    }

    // Scanner leggerà e scarterà tutti gli input fino a quando non leggerà un Enter
    public static void waitEnter() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("")) {
            input = scanner.nextLine();
        }
    }
}
