package com.example.campuscarpool.graphiccontroller;

import com.example.campuscarpool.Main;
import com.example.campuscarpool.appcontroller.LoginController;
import com.example.campuscarpool.bean.LoginBean;
import com.example.campuscarpool.engineering.Printer;
import com.example.campuscarpool.engineering.ShowExceptionSupport;
import com.example.campuscarpool.exception.EmailFormatException;
import com.example.campuscarpool.exception.NotFoundException;
import com.example.campuscarpool.exception.UserNotFoundException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginGUIController {
    @FXML
    TextField emailTextField;

    @FXML
    PasswordField pswdTextField;

    private static final String NOT_IMPLEMENTED = "Not implemented yet!";

    public void checkLogin() throws IOException, NotFoundException {

        try {
            //  Creazione LoginBean con i dati inseriti dall'utente
            LoginBean loginBean = new LoginBean(emailTextField.getText(), pswdTextField.getText());
            LoginController loginController = new LoginController();

            //  Verifica dell'utente
            loginController.checkUser(loginBean);

            Parent root;
            Stage dialog = Main.getStage();

            switch (loginBean.getRole()) {
                case 1 -> {
                    loginController.driverLogin(loginBean);
                    root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("driverHomepage.fxml")));
                }

                case 2 -> {
                    loginController.passengerLogin(loginBean);
                    root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("passengerHomepage.fxml")));
                }

                default -> throw new UserNotFoundException();
            }

            Scene scene = new Scene(root);
            dialog.setScene(scene);
            dialog.show();

        } catch (EmailFormatException | UserNotFoundException e) {
            ShowExceptionSupport.showException(e.getMessage());
        } catch (IOException e) {
            Printer.printError(e.getMessage());
        }
    }

    //  Per effettuare il login con Facebook
    public void facebookLogin() throws IOException {
        ShowExceptionSupport.showException(NOT_IMPLEMENTED);
    }

    //  Per effettuare il login con Google
    public void googleLogin() throws IOException {
        ShowExceptionSupport.showException(NOT_IMPLEMENTED);
    }

    //  Per creare un nuovo account
    public void createNewAccount() throws IOException {
        ShowExceptionSupport.showException(NOT_IMPLEMENTED);
    }
}