package com.example.campuscarpool.graphiccontroller;

import com.example.campuscarpool.Main;
import com.example.campuscarpool.appcontroller.SearchRideController;
import com.example.campuscarpool.bean.*;
import com.example.campuscarpool.engineering.ShowExceptionSupport;
import com.example.campuscarpool.exception.FormEmptyException;
import com.example.campuscarpool.exception.MessageException;
import com.example.campuscarpool.exception.NotFoundException;
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
import java.util.List;
import java.util.Objects;

public class SearchRideGUIController {

    @FXML
    private Spinner<Integer> hourSpinner;
    @FXML
    private Spinner<Integer> minuteSpinner;
    @FXML
    private DatePicker dateDataPicker;
    @FXML
    private TextField destinationLocationTextField;
    @FXML
    private TextField departureLocationTextField;

    public void initialize() {

        //  Inizializzazione degli Spinner con i rispettivi valori possibili
        hourSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));
        hourSpinner.getValueFactory().setConverter(new IntegerStringConverter());

        minuteSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59));
        minuteSpinner.getValueFactory().setConverter(new IntegerStringConverter());

        // Listener per formattare i primi 10 minuti con due cifre
        minuteSpinner.getValueFactory().valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue >= 0 && newValue <= 9) {
                minuteSpinner.getEditor().setText(String.format("0%d", newValue));
            }
        });

        // Listener per formattare le prime ore con due cifre
        hourSpinner.getValueFactory().valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue >= 0 && newValue <= 9) {
                hourSpinner.getEditor().setText(String.format("0%d", newValue));
            }
        });

        //  Per impostare, di default, la data e l'orario corrente
        minuteSpinner.getValueFactory().setValue(LocalTime.now().getMinute());
        dateDataPicker.setValue(LocalDate.now());
        hourSpinner.getValueFactory().setValue(LocalTime.now().getHour());
    }

    //  Per inviare i criteri una volta cliccato il bottone
    public void toCompatibleRides() throws IOException {

        try {

            //  Serie di controlli per verificare che i campi siano compilati correttamente
            if (hourSpinner.getValue() == null)
                throw new FormEmptyException("Hour");

            if (minuteSpinner.getValue() == null)
                throw new FormEmptyException("Minute");

            if (dateDataPicker.getValue() == null)
                throw new FormEmptyException("Date");

            if (destinationLocationTextField.getText().trim().isEmpty())
                throw new FormEmptyException("Destination Location");

            if (departureLocationTextField.getText().trim().isEmpty())
                throw new FormEmptyException("Departure Location");

            //  Creazione SearchRideBean con le informazioni inserite
            SearchRideBean searchRideBean = new SearchRideBean(dateDataPicker.getValue(), getTime(),
                    departureLocationTextField.getText().trim(), destinationLocationTextField.getText().trim());

            SearchRideController searchRideController = new SearchRideController();
            List<CompatibleRideBean> rides = searchRideController.compatibleRides(searchRideBean);

            Stage stage = Main.getStage();

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(Main.class.getResource("compatibleRides.fxml"));

            Scene scene = new Scene(fxmlLoader.load());

            CompatibleRidesGUIController compatibleRidesGUIController = fxmlLoader.getController();
            int count = compatibleRidesGUIController.displayCompatibleRides(rides);

            if(count == 0) {
                throw new MessageException("Compatible rides not found!");
            } else {
                stage.setScene(scene);
                stage.show();
            }
        } catch (FormEmptyException | MessageException | NotFoundException e) {
            ShowExceptionSupport.showException(e.getMessage());
        }
    }

    //  Per visualizzare i dettagli del profilo
    public void toProfile() throws IOException {
        ShowExceptionSupport.showException("Not implemented yet!");
    }

    //  Per definire l'orario in base ai valori negli spinner
    public LocalTime getTime() {
        int hour = hourSpinner.getValue();
        int minute = minuteSpinner.getValue();

        return LocalTime.of(hour, minute);
    }

    //  Per tornare alla schermata precedente
    public void toPreviousScreen() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("passengerHomepage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //  Per effettuare il logout
    public void logout() throws IOException {
        LogoutGUIController logoutGUIController = new LogoutGUIController();
        logoutGUIController.logout();
    }

    //  Per tornare alla homepage
    public void toHomepage() throws IOException {
        Stage stage = Main.getStage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("passengerHomepage.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}