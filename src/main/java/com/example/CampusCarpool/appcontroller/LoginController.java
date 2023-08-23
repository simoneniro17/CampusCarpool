package com.example.CampusCarpool.appcontroller;

import com.example.CampusCarpool.bean.DriverBean;
import com.example.CampusCarpool.bean.LoginBean;
import com.example.CampusCarpool.bean.PassengerBean;
import com.example.CampusCarpool.dao.DriverDAO;
import com.example.CampusCarpool.dao.LoginDAO;
import com.example.CampusCarpool.dao.PassengerDAO;
import com.example.CampusCarpool.engineering.Session;
import com.example.CampusCarpool.engineering.factory.LoginDAOFactory;
import com.example.CampusCarpool.exception.NotFoundException;
import com.example.CampusCarpool.exception.UserNotFoundException;
import com.example.CampusCarpool.model.Driver;
import com.example.CampusCarpool.model.Passenger;
import com.example.CampusCarpool.model.UserProfile;

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