package com.example.CampusCarpool.engineering;

import java.util.Scanner;

// Questa classe fornisce metodi per attendere che l'utente premi il tasto "invio"
public class ScannerSupport {

    // Costruttore privato per prevenire l'istanziazione, visto che tutti i metodi sono statici
    private ScannerSupport() {

    }

    // Lo Scanner leggerà e scarterà tutti gli input fino a quando non leggerà un Enter
    public static void waitEnter() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals("")) {
            input = scanner.nextLine();
        }
    }
}
