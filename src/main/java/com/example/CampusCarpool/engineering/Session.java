package com.example.CampusCarpool.engineering;

import com.example.CampusCarpool.bean.DriverBean;
import com.example.CampusCarpool.bean.PassengerBean;

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

    public static void setSessionInstance(Object obj) {
        // Viene creata una nuova sessione solo se non esiste già una sessione attiva
        if(sessionInstance == null)
            sessionInstance = new Session(obj);
    }

    // Metodo per chiudere la sessione corrente
    public static void closeSession() {
        sessionInstance = null;
    }

    public static Session getCurrentSession() {
        return sessionInstance;
    }

    // Metodo per restituire l'oggetto DriverBean associato alla sessione corrente
    public DriverBean getDriverBean() {
        return driverBean;
    }

    // Metodo per restituire l'oggetto PassengerBean associato alla sessione corrente
    public PassengerBean getPassengerBean() {
        return passengerBean;
    }
}
