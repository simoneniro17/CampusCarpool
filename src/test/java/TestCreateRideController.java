import com.example.campuscarpool.appcontroller.CreateRideController;
import com.example.campuscarpool.bean.RideBean;
import com.example.campuscarpool.exception.MessageException;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCreateRideController {

    /* Il seguente test verifica che venga sollevata un'eccezione quando viene creata una corsa con
        una data di partenza precedente a quella corrente */
    @Test
    void testCreateRide() {
        int validDate = 0;
        RideBean rideBean = new RideBean(LocalDate.parse("2023-06-14"), LocalTime.parse("10:00:00"), "A", "B", 2);
        rideBean.setRideBeanDriverInfo("Simone", "Niro", "driver1@gmail.com", "3731044423");

        CreateRideController createRideController = new CreateRideController();

        try {
            createRideController.createRide(rideBean);
            validDate = 1;
        } catch (MessageException e) {
            validDate = 2;
        } catch (Exception ignore) {
        }

        // Il test ha successo perché la data di partenza è precedente al giorno corrente e viene sollevata l'eccezione
        assertEquals(2, validDate);
    }
}