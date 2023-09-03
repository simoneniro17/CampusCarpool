import com.example.campuscarpool.bean.LoginBean;
import com.example.campuscarpool.exception.EmailFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestLoginBean {

    /* Verifichiamo se viene lanciata l'eccezione EmailFormatException quando
       viene creata un'istanza di LoginBean passando una mail con un formato sintatticamente errato */
   @Test
    void testValidEmail() {

        int validFormat;

        try {
            new LoginBean("simoneniro@", "passenger1");
            validFormat = 1;
        } catch (EmailFormatException e) {
            validFormat = 0;
        }

        // Il test ha successo perché l'eccezione viene sollevata assegnando il valore 0 a validFormat
        assertEquals(0, validFormat);
    }

}