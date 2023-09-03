package com.example.campuscarpool.graphiccontroller.cli;

import com.example.campuscarpool.appcontroller.LoginController;
import com.example.campuscarpool.bean.LoginBean;
import com.example.campuscarpool.engineering.ShowExceptionSupport;
import com.example.campuscarpool.exception.*;
import com.example.campuscarpool.viewcli.LoginViewCLI;

public class LoginCLIController implements GraphicCLIController {

    // Costanti per le opzioni di login
    private static final String LOGIN = "1";
    private static final String LOGIN_WITH_FACEBOOK = "2";
    private static final String LOGIN_WITH_GOOGLE = "3";
    private static final String SIGN_UP = "4";

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

            // Utente riconosciuto come guidatore
            if (loginBean.getRole() == 1) {
                loginController.driverLogin(loginBean);

                DriverCLIController driverCLIController = new DriverCLIController();
                driverCLIController.start();
            }
            // Utente riconosciuto come passeggero
            else if (loginBean.getRole() == 2) {
                loginController.passengerLogin(loginBean);

                PassengerCLIController passengerCLIController = new PassengerCLIController();
                passengerCLIController.start();
            }
            // Utente non riconosciuto
            else {
                throw new UserNotFoundException();
            }
        } catch (EmailFormatException | NotFoundException | UserNotFoundException e) {
            ShowExceptionSupport.showExceptionCLI(e.getMessage());
            start();
        }
    }
}