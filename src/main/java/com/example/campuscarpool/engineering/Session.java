package com.example.campuscarpool.engineering;

import com.example.campuscarpool.bean.DriverBean;
import com.example.campuscarpool.bean.PassengerBean;

// Sessione utente che memorizza le info del conducente e del passeggero
public class Session {

    private static Session sessionInstance = null;
    private DriverBean driverBean;
    private PassengerBean passengerBean;

    // Costruttore privato per inizializzare la sessione con un oggetto specifico
    private Session (Object obj) {
        if(obj instanceof DriverBean) {
            this.driverBean = (DriverBean) obj;
        } else if (obj instanceof PassengerBean) {
            passengerBean = (PassengerBean) obj;
        }
    }

    // Impostazione istanza della sessione solo se non ne esiste già una attiva
    public static void setSessionInstance(Object obj) {
        if(sessionInstance == null)
            sessionInstance = new Session(obj);
    }

    // Chiude la sessione corrente
    public static void closeSession() {
        sessionInstance = null;
    }

    // Restituisce l'istanza corrente della sessione
    public static Session getCurrentSession() {
        return sessionInstance;
    }

    // Restituisce l'oggetto DriverBean associato alla sessione corrente
    public DriverBean getDriverBean() {
        return driverBean;
    }

    // Restituisce l'oggetto PassengerBean associato alla sessione corrente
    public PassengerBean getPassengerBean() {
        return passengerBean;
    }
}
