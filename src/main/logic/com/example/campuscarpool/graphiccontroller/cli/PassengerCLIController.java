package com.example.campuscarpool.graphiccontroller.cli;

import com.example.campuscarpool.appcontroller.LogoutController;
import com.example.campuscarpool.exception.CommandErrorException;
import com.example.campuscarpool.exception.NotImplementedException;
import com.example.campuscarpool.viewcli.PassengerViewCLI;

public class PassengerCLIController implements GraphicCLIController {

    // Costanti per le opzioni del menu
    private static final String SEARCH_RIDE = "1";
    private static final String MY_REQUESTS = "2";
    private static final String VIEW_PROFILE = "3";
    private static final String LOGOUT = "4";

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

                // Ritorno al menu del passenger dopo aver completato l'operazione
                this.start();
            }

            case VIEW_PROFILE -> throw new NotImplementedException();

            case LOGOUT -> {
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