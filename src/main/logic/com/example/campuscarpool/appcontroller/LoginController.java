package com.example.campuscarpool.appcontroller;

import com.example.campuscarpool.bean.DriverBean;
import com.example.campuscarpool.bean.LoginBean;
import com.example.campuscarpool.bean.PassengerBean;
import com.example.campuscarpool.dao.DriverDAO;
import com.example.campuscarpool.dao.LoginDAO;
import com.example.campuscarpool.dao.PassengerDAO;
import com.example.campuscarpool.engineering.Session;
import com.example.campuscarpool.engineering.factory.LoginDAOFactory;
import com.example.campuscarpool.exception.NotFoundException;
import com.example.campuscarpool.exception.UserNotFoundException;
import com.example.campuscarpool.model.Driver;
import com.example.campuscarpool.model.Passenger;
import com.example.campuscarpool.model.UserProfile;

public class LoginController {

    // Verifica utente con le credenziali fornite nel LoginBean
    public void checkUser(LoginBean loginBean) throws UserNotFoundException {

        // Ottenimento istanza del DAO per il controllo delle credenziali
        LoginDAO loginDAO = LoginDAOFactory.getInstance().createLoginDAO();

        // Controllo dell'utente tramite il DAO e ottenimento del relativo ruolo
        UserProfile userProfile = loginDAO.checkUser(loginBean.getUsername(), loginBean.getPassword());
        loginBean.setRole(userProfile.getRole());
    }

    // Login del driver dopo il controllo delle credenziali
    public void driverLogin(LoginBean loginBean) throws NotFoundException {

        // Driver corrispondente al nome utente nel DAO
        Driver driver = DriverDAO.findDriverByUsername(loginBean.getUsername());

        // Creazione DriverBean per il driver autenticato
        DriverBean driverBean = new DriverBean(driver.getFirstName(), driver.getLastName(), driver.getDateOfBirth(), driver.getGender(),
                driver.getEmail(), driver.getPhoneNumber());

        // Impostazione sessione con il DriverBean appena creato
        Session.setSessionInstance(driverBean);
    }

    // Login del passeggero dopo il controllo delle credenziali
    public void passengerLogin(LoginBean loginBean) throws NotFoundException {

        // Passenger corrispondente al nome utente nel DAO
        Passenger passenger = PassengerDAO.findPassengerByUsername(loginBean.getUsername());

        // Creazione PassengerBean per il passenger autenticato
        PassengerBean passengerBean = new PassengerBean(passenger.getFirstName(), passenger.getLastName(), passenger.getDateOfBirth(),
                passenger.getGender(), passenger.getEmail(), passenger.getPhoneNumber());

        // Impostazione sessione con il PassengerBean appena creato
        Session.setSessionInstance(passengerBean);
    }
}