package com.example.CampusCarpool.graphiccontroller.cli;

import com.example.CampusCarpool.appcontroller.LoginController;
import com.example.CampusCarpool.bean.LoginBean;
import com.example.CampusCarpool.engineering.ShowExceptionSupport;
import com.example.CampusCarpool.exception.*;
import com.example.CampusCarpool.viewcli.LoginViewCLI;

public class LoginCLIController implements GraphicCLIController {

    // Costanti per le opzioni di login
    private static final String LOGIN = "1";
    private static final String LOGIN_WITH_FACEBOOK = "2";
    private static final String LOGIN_WITH_GOOGLE = "3";
    private static final String SIGN_UP = "4";

    // View del login in interfaccia a riga di comando
    private LoginViewCLI loginViewCLI;

    @Override
    public void start() {
        // Avvio della view del login
        this.loginViewCLI = new LoginViewCLI(this);
        this.loginViewCLI.run();
    }

    // Esecuzione comando specificato dall'utente
    public void executeCommand(String inputLine) throws CommandErrorException, NotImplementedException {

        switch (inputLine) {

            // Avvio della procedura di login
            case LOGIN -> this.loginViewCLI.getCredentials();

            // Altre opzioni non implementate
            case LOGIN_WITH_FACEBOOK, LOGIN_WITH_GOOGLE, SIGN_UP -> throw new NotImplementedException();

            // Se il comando non è riconosciuto, viene lanciata un'eccezione
            default -> throw new CommandErrorException();
        }
    }

    // Verifica delle credenziali inserite dall'utente
    public void checkLogin(String email, String password) {
        try {

            // Creazione di un LoginBean con le credenziali inserite
            LoginBean loginBean = new LoginBean(email, password);
            LoginController loginController = new LoginController();

            // Verifica dell'utente
            loginController.checkUser(loginBean);

            if (loginBean.getRole() == 1) {

                // Utente riconosciuto come guidatore
                loginController.driverLogin(loginBean);
                DriverCLIController driverCLIController = new DriverCLIController();

                // Avvio schermata del guidatore
                driverCLIController.start();

            } else if (loginBean.getRole() == 2) {

                // Utente riconosciuto come passeggero
                loginController.passengerLogin(loginBean);
                PassengerCLIController passengerCLIController = new PassengerCLIController();

                // Avvio schermata del passeggero
                passengerCLIController.start();

            } else {

                // Utente non riconosciuto
                throw new UserNotFoundException();
            }

        } catch (EmailFormatException | NotFoundException | UserNotFoundException e) {

            //  Messaggio di errore in caso di eccezione e riavvio schermata di login
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            start();
        }
    }
}
