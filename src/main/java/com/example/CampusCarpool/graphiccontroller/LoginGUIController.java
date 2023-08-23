package com.example.CampusCarpool.graphiccontroller;

import com.example.CampusCarpool.Main;
import com.example.CampusCarpool.appcontroller.LoginController;
import com.example.CampusCarpool.bean.LoginBean;
import com.example.CampusCarpool.engineering.Printer;
import com.example.CampusCarpool.engineering.ShowExceptionSupport;
import com.example.CampusCarpool.exception.EmailFormatException;
import com.example.CampusCarpool.exception.NotFoundException;
import com.example.CampusCarpool.exception.UserNotFoundException;
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


    public void checkLogin() throws IOException, NotFoundException {

        try {
            LoginBean loginBean = new LoginBean(emailTextField.getText(), pswdTextField.getText());
            LoginController loginController = new LoginController();
            loginController.checkUser(loginBean);

            Parent root;
            Stage dialog = Main.getStage();

            switch(loginBean.getRole()) {
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

    public void facebookLogin() throws IOException {
        ShowExceptionSupport.showException("Not implemented yet!");
    }

    public void googleLogin() throws IOException {
        ShowExceptionSupport.showException("Not implemented yet!");
    }

    public void createNewAccount() throws IOException {
        ShowExceptionSupport.showException("Not implemented yet!");
    }
}