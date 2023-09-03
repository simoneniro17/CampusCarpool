import com.example.campuscarpool.dao.LoginDAO;
import com.example.campuscarpool.engineering.factory.LoginDAOFactory;
import com.example.campuscarpool.exception.UserNotFoundException;
import com.example.campuscarpool.model.UserProfile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestLoginDAO {

    /* Verifichiamo che venga ritornato il tipo di ruolo corretto quando
       viene effettuato il login con il profilo di un passeggero */
    @Test
    void testCheckUser() throws UserNotFoundException {
        LoginDAO loginDAO = LoginDAOFactory.getInstance().createLoginDAO();
        UserProfile userProfile = loginDAO.checkUser("passenger1@gmail.com", "passenger1");

        // Il test ha successo in quanto restituisce il ruolo 2, corrispondente al passeggero
        assertEquals(2, userProfile.getRole());
    }
}