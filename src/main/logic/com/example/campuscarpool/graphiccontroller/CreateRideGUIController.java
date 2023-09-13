package com.example.campuscarpool.graphiccontroller;

import com.example.campuscarpool.Main;
import com.example.campuscarpool.appcontroller.CreateRideController;
import com.example.campuscarpool.bean.DriverBean;
import com.example.campuscarpool.bean.RideBean;
import com.example.campuscarpool.engineering.Session;
import com.example.campuscarpool.engineering.ShowExceptionSupport;
import com.example.campuscarpool.exception.DuplicateRideException;
import com.example.campuscarpool.exception.FormEmptyException;
import com.example.campuscarpool.exception.MessageException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class CreateRideGUIController {

    @FXML
    private DatePicker dateDataPicker;

    @FXML
    private Spinner<Integer> hourSpinner;

    @FXML
    private Spinner<Integer> minuteSpinner;

    @FXML
    private TextField departureLocationTextField;

    @FXML
    private TextField destinationLocationTextField;

    @FXML
    private Spinner<Integer> availableSeatsSpinner;

    private static final String DRIVER_HP = "driverHomepage.fxml";

    public void initialize() {

        //  Inizializzazione degli Spinner con i rispettivi valori possibili
        hourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));
        hourSpinner.getValueFactory().setConverter(new IntegerStringConverter());

        minuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        minuteSpinner.getValueFactory().setConverter(new IntegerStringConverter());

        availableSeatsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4));
        availableSeatsSpinner.getValueFactory().setConverter(new IntegerStringConverter());

        // Listener per formattare le prime ore con due cifre
        hourSpinner.getValueFactory().valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue >= 0 && newValue <= 9) {
                hourSpinner.getEditor().setText(String.format("0%d", newValue));
            }
        });

        // Listener per formattare i primi 10 minuti con due cifre
        minuteSpinner.getValueFactory().valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue >= 0 && newValue <= 9) {
                minuteSpinner.getEditor().setText(String.format("0%d", newValue));
            }
        });

        //  Per impostare, di default, la data e l'orario corrente
        dateDataPicker.setValue(LocalDate.now());
        hourSpinner.getValueFactory().setValue(LocalTime.now().getHour());
        minuteSpinner.getValueFactory().setValue(LocalTime.now().getMinute());
    }

    //  Per creare la corsa una volta cliccato il bottone
    @FXML
    private void createRide(ActionEvent event) throws IOException {

        // Recupero informazioni dell'attuale Driver
        DriverBean driverBean = Session.getCurrentSession().getDriverBean();
        String driverEmail = driverBean.getEmail();
        String driverFirstName = driverBean.getFirstName();
        String driverLastName = driverBean.getLastName();
        String driverPhoneNumber = driverBean.getPhoneNumber();

        try {

            //  Serie di controlli per verificare che i campi siano compilati correttamente
            if (dateDataPicker.getValue() == null)
                throw new FormEmptyException("Date");

            if (hourSpinner.getValue() == null)
                throw new FormEmptyException("Hour");

            if (minuteSpinner.getValue() == null)
                throw new FormEmptyException("Minute");

            if (departureLocationTextField.getText().trim().isEmpty())
                throw new FormEmptyException("Departure Location");

            if (destinationLocationTextField.getText().trim().isEmpty())
                throw new FormEmptyException("Destination Location");

            if (availableSeatsSpinner.getValue() == null)
                throw new FormEmptyException("Available Seats");

            //  Creazione RideBean con le informazioni inserite
            RideBean rideBean = new RideBean(dateDataPicker.getValue(), getTime(), departureLocationTextField.getText().trim(),
                    destinationLocationTextField.getText().trim(), availableSeatsSpinner.getValue());
            rideBean.setRideBeanDriverInfo(driverFirstName, driverLastName, driverEmail, driverPhoneNumber);

            //  Creazione corsa
            CreateRideController createRideController = new CreateRideController();
            createRideController.createRide(rideBean);

            ShowExceptionSupport.showException("Ride created!\nYou have been redirected to homepage.");
            toHomepage();
        } catch (FormEmptyException | DuplicateRideException | MessageException e) {
            ShowExceptionSupport.showException(e.getMessage());
        }
    }

    //  Per definire l'orario in base ai valori negli spinner
    public LocalTime getTime() {
        int hour = hourSpinner.getValue();
        int minute = minuteSpinner.getValue();

        return LocalTime.of(hour, minute);
    }

    //  Per effettuare il logout
    public void logout() throws IOException {
        LogoutGUIController logoutGUIController = new LogoutGUIController();
        logoutGUIController.logout();
    }

    //  Per visualizzare i dettagli del profilo
    public void toProfile() throws IOException {
        ShowExceptionSupport.showException("Not implemented yet!");
    }

    //  Per tornare alla homepage
    public void toHomepage() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(DRIVER_HP)));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //  Per tornare alla schermata precedente
    public void toPreviousScreen() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(DRIVER_HP)));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
