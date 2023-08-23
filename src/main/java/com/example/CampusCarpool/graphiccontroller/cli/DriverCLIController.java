package com.example.CampusCarpool.graphiccontroller.cli;

import com.example.CampusCarpool.appcontroller.LogoutController;
import com.example.CampusCarpool.exception.CommandErrorException;
import com.example.CampusCarpool.exception.NotImplementedException;
import com.example.CampusCarpool.viewcli.DriverViewCLI;

public class DriverCLIController implements GraphicCLIController {

    // Costanti per le opzioni del menu
    private static final String CREATE_RIDE = "1";
    private static final String CHECK_REQUESTS = "2";
    private static final String VIEW_PROFILE = "3";
    private static final String LOGOUT = "4";

    // ViewCLI per la gestione delle interazioni con la UI
    DriverViewCLI driverViewCLI;

    @Override
    public void start() {
        this.driverViewCLI = new DriverViewCLI(this);
        this.driverViewCLI.run();
    }

    // Esecuzione comando specificato dall'utente
    public void executeCommand(String inputLine) throws CommandErrorException, NotImplementedException {

        switch(inputLine) {
            case CREATE_RIDE -> {

                // Avviamento controller per la creazione di una corsa
                CreateRideCLIController createRideCLIController = new CreateRideCLIController();
                createRideCLIController.start();

                // Ritorno al menu del driver dopo aver completato l'operazione
                this.start();
            }

            case VIEW_PROFILE -> {
                throw new NotImplementedException();
            }

            case CHECK_REQUESTS -> {

                // Avviamento controller per la gestione delle richieste
                DriverRequestsCLIController driverRequestsCLIController = new DriverRequestsCLIController();
                driverRequestsCLIController.start();

                // Ritorno al menu del driver dopo aver completato l'operazione
                this.start();
            }

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
