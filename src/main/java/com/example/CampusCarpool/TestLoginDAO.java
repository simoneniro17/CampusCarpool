package com.example.CampusCarpool;

import com.example.CampusCarpool.dao.LoginDAO;
import com.example.CampusCarpool.engineering.factory.LoginDAOFactory;
import com.example.CampusCarpool.exception.UserNotFoundException;
import com.example.CampusCarpool.model.UserProfile;

class TestLoginDAO {

    /* Verifichiamo che venga ritornato il tipo di ruolo corretto quando
       viene effettuato il login con il profilo di una passeggero */

/*    @Test
    void testCheckUser() throws UserNotFoundException {
        LoginDAO loginDAO = LoginDAOFactory.getInstance().createLoginDAO();
        UserProfile userProfile = loginDAO.checkUser("passenger1@gmail.com", "passenger1");

        // Il test ha successo in quanto restituisce il ruolo 2, corrispondente al passeggero
        Assertions.assertEquals(2, userProfile.getRole());
    } */
}