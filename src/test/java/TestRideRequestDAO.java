import com.example.CampusCarpool.connection.ConnectionDB;
import com.example.CampusCarpool.dao.RideRequestDAO;
import com.example.CampusCarpool.dao.queries.RetrieveQueries;
import com.example.CampusCarpool.exception.NotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestRideRequestDAO {

    /* Il seguente test verifica che dopo l'aggiunta di una nuova richiesta per una corsa, chiamando un metodo
     * del DAO che restituisce il numero di richiesta per quella corsa, questo venga incrementato di 1 */

    @Test
    void testNewRequest() {
        Connection connection;
        ResultSet resultSet;
        int count = 0;
        int newCount = 0;

        try {
            connection = ConnectionDB.getConnection();
            resultSet = RetrieveQueries.getNumberOfRequests(connection, 5);

            if (!resultSet.first()) {
                throw new NotFoundException("No ride found");
            }

            resultSet.first();
            count = resultSet.getInt("requests");

            RideRequestDAO.newRequest(5, "passenger2@gmail.com");

            resultSet = RetrieveQueries.getNumberOfRequests(connection, 5);

            if (!resultSet.first()) {
                throw new NotFoundException("No ride found");
            }

            resultSet.first();
            newCount = resultSet.getInt("requests");
        } catch (Exception ignore) {
        }

        // Il test ha successo perché la richiesta viene aggiunta correttamente
        assertEquals(count + 1, newCount);
    }
}