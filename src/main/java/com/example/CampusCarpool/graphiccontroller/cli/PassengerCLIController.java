package com.example.CampusCarpool.graphiccontroller.cli;

import com.example.CampusCarpool.appcontroller.LogoutController;
import com.example.CampusCarpool.exception.CommandErrorException;
import com.example.CampusCarpool.exception.NotImplementedException;
import com.example.CampusCarpool.viewcli.PassengerViewCLI;

public class PassengerCLIController implements GraphicCLIController {

    // Costanti per le opzioni del menu
    private final static String SEARCH_RIDE = "1";
    private final static String MY_REQUESTS = "2";
    private final static String VIEW_PROFILE = "3";
    private final static String LOGOUT = "4";

    // ViewCLI per la gestione delle interazioni con la UI
    PassengerViewCLI passengerViewCLI;

    @Override
    public void start() {
        this.passengerViewCLI = new PassengerViewCLI(this);
        this.passengerViewCLI.run();
    }

    // Esecuzione comando specificato dall'utente
    public void executeCommand(String inputLine) throws CommandErrorException, NotImplementedException {

        switch(inputLine) {
            case SEARCH_RIDE -> {
                SearchRideCLIController searchRideCLIController = new SearchRideCLIController();
                searchRideCLIController.start();

                // Ritorno al menu del passenger dopo aver completato l'operazione
                this.start();
            }

            case MY_REQUESTS -> {
                PassengerRequestsCLIController passengerRequestsCLIController = new PassengerRequestsCLIController();
                passengerRequestsCLIController.start();

                this.start();
            }

            case VIEW_PROFILE -> throw new NotImplementedException();

            case LOGOUT -> {

                // Logout dell'utente corrente
                LogoutController logoutController = new LogoutController();
                logoutController.logout();

                // Ritorno alla schermata di login
                LoginCLIController loginCLIController = new LoginCLIController();
                loginCLIController.start();
            }

            default -> throw new CommandErrorException();
        }
    }
}